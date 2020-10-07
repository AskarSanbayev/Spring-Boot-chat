package com.chat.cyber.service.impl;

import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.Post;
import com.chat.cyber.repo.PostRepository;
import com.chat.cyber.service.PostService;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
        }
    }

    @Override
    public void create(Post post) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Post post) {
        if (postRepository.findById(post.getId()).isPresent()) {
            postRepository.save(post);
        }
    }

    @Override
    public Post findById(Long id) {
        return (postRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void create(Post post, Principal principal) {
//        User author = userService.findByLogin(user.getUsername());
//        post.setAuthor(author);
        postRepository.save(post);
    }
}
