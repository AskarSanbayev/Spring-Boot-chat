package com.chat.cyber.service.impl;

import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.Post;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.PostRepository;
import com.chat.cyber.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
        } else {
            throw new EntityNotFoundException("Post not found");
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
        } else {
            throw new EntityNotFoundException("Post not found");
        }
    }

    @Override
    public Post findById(Long id) {
        return (postRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void create(Post post, User user) {
        post.setAuthor(user);
        postRepository.save(post);
    }
}
