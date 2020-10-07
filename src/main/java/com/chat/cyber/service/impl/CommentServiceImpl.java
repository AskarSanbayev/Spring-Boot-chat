package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.CommentDto;
import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.Comment;
import com.chat.cyber.model.Post;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.CommentRepository;
import com.chat.cyber.service.CommentService;
import com.chat.cyber.service.PostService;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private ProfileService profileService;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(EntityNotFoundException::new);
        comment.setText(commentDto.getText());
        comment.setLastModifiedDate(new Date());
        commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void create(CommentDto commentDto, Principal principal) {
        User author = userService.findById(profileService.getUuid(principal));
        Post post = postService.findById(commentDto.getPostUuid());
        Comment comment = new Comment();
        Date createDate = new Date();
        comment.setCreationDate(createDate);
        comment.setLastModifiedDate(createDate);
        comment.setAuthor(author);
        comment.setPost(post);
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
    }
}
