package com.chat.cyber.service;

import com.chat.cyber.model.Comment;
import com.chat.cyber.model.Post;
import com.chat.cyber.model.User;

public interface CommentService extends BaseService<Comment, Long> {

    void create(Comment comment,Post post, User user);

}
