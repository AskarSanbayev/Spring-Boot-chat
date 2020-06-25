package com.chat.cyber.service;

import com.chat.cyber.model.Comment;
import com.chat.cyber.security.jwt.JwtUser;

public interface CommentService extends BaseService<Comment, Long> {

    void create(Comment comment, Long postId, JwtUser user);

}
