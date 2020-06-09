package com.chat.cyber.service.impl;

import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.Comment;
import com.chat.cyber.model.Post;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.CommentRepository;
import com.chat.cyber.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Comment not found");
        }
    }

    @Override
    public void update(Comment comment) {
        if (commentRepository.findById(comment.getId()).isPresent()) {
            commentRepository.save(comment);
        } else {
            throw new EntityNotFoundException("Comment not found");
        }
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void create(Comment comment, Post post, User user) {
        comment.setAuthor(user);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public void create(Comment comment) {
        throw new UnsupportedOperationException();
    }
}
