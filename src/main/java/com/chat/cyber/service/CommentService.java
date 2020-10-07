package com.chat.cyber.service;

import com.chat.cyber.model.Comment;

import java.security.Principal;

public interface CommentService extends BaseService<Comment, Long> {

    void create(Comment comment, Long postId, Principal principal);

}
