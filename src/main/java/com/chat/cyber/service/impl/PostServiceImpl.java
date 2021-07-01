package com.chat.cyber.service.impl;

import com.chat.cyber.comp.PermissionHelper;
import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.Post;
import com.chat.cyber.model.UserLike;
import com.chat.cyber.repo.PostRepository;
import com.chat.cyber.service.PostService;
import com.chat.cyber.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ProfileService profileService;
    private final PermissionHelper permissionHelper;

    @Override
    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> findAllByAuthor(Principal principal) {
        final Long authorId = profileService.getId(principal);
        return postRepository.findByAuthorId(authorId);
    }

    @Override
    public void deleteById(Principal principal, Long postId) {
        final Long authorId = profileService.getId(principal);
        Post post = postRepository.findById(postId).orElseThrow(RestException::new);
        permissionHelper.checkPostEditPermission(authorId, post);
        postRepository.deleteById(postId);
    }

    @Override
    public void create(PostDto postDto, Principal principal) {
        final Long authorId = profileService.getId(principal);
        Post post = new Post();
        Date createDate = new Date();
        post.setText(postDto.getText());
        post.setCreationDate(createDate);
        post.setLastModifiedDate(createDate);
        post.setAuthorId(authorId);
        postRepository.save(post);
    }

    @Override
    public void update(Principal principal, PostDto postDto) {
        final Long authorId = profileService.getId(principal);
        Post post = postRepository.findById(postDto.getId()).orElseThrow(RestException::new);
        post.setText(postDto.getText());
        post.setLastModifiedDate(new Date());
        permissionHelper.checkPostEditPermission(authorId, post);
        postRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(RestException::new);
    }
}
