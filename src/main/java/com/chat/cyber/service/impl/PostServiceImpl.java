package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.Post;
import com.chat.cyber.model.User;
import com.chat.cyber.model.UserLike;
import com.chat.cyber.repo.PostRepository;
import com.chat.cyber.service.PostService;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;

    @Override
    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> findAllByAuthor(Principal principal) {
        return postRepository.findByAuthorUuid(profileService.getUuid(principal));
    }

    @Override
    public void deleteById(String uuid) {
        postRepository.deleteByUuid(uuid);
    }

    @Override
    public void create(PostDto postDto, Principal principal) {
        User author = userService.findByUUid(profileService.getUuid(principal));
        Post post = new Post();
        Date createDate = new Date();
        post.setText(postDto.getText());
        post.setUuid(UUID.randomUUID().toString());
        post.setCreationDate(createDate);
        post.setLastModifiedDate(createDate);
        post.setAuthor(author);
        post.setUserLike(new UserLike());
        postRepository.save(post);
    }

    @Override
    public void update(PostDto postDto) {
        Post post = postRepository.findByUuid(postDto.getUuid()).orElseThrow(RestException::new);
        post.setText(postDto.getText());
        post.setLastModifiedDate(new Date());
        postRepository.save(post);
    }

    @Override
    public Post findById(String uuid) {
        return postRepository.findByUuid(uuid).orElseThrow(RestException::new);
    }
}
