package com.chat.cyber.service;

import com.chat.cyber.dto.request.CommentDto;
import com.chat.cyber.model.Comment;

import java.security.Principal;

public interface CommentService extends BaseService<Comment, Long> {

    void create(CommentDto comment, Principal principal);

    void update(CommentDto comment);
}
