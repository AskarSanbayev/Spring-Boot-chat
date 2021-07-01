package com.chat.cyber.service;

import com.chat.cyber.dto.request.CommentDto;
import com.chat.cyber.model.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentService {

    void create(CommentDto comment, Principal principal);

    void update(Principal principal, CommentDto comment);

    List<Comment> findAll();

    void deleteById(Principal principal, Long commentId);

    Comment findById(Long id);
}
