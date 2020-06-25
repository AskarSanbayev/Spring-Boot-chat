package com.chat.cyber.service.impl;

import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.Comment;
import com.chat.cyber.model.Post;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.CommentRepository;
import com.chat.cyber.security.jwt.JwtUser;
import com.chat.cyber.service.CommentService;
import com.chat.cyber.service.PostService;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              UserService userService,
                              PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
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
        }
    }

    @Override
    public void update(Comment comment) {
        if (commentRepository.findById(comment.getId()).isPresent()) {
            commentRepository.save(comment);
        }
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void create(Comment comment, Long postId, JwtUser user) {
        User author = userService.findByLogin(user.getUsername());
        Post post = postService.findById(postId);
        comment.setAuthor(author);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public void create(Comment comment) {
        throw new UnsupportedOperationException();
    }
}
