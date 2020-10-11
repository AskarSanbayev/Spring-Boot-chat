package com.chat.cyber.service;

import com.chat.cyber.dto.request.CommentDto;
import com.chat.cyber.model.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentService {

    void create(CommentDto comment, Principal principal);

    void update(CommentDto comment);

    List<Comment> findAll();

    void deleteById(Long id);

    Comment findById(Long id);
}
