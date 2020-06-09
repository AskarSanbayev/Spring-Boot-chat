package com.chat.cyber.service;

import com.chat.cyber.model.Post;
import com.chat.cyber.model.User;

public interface PostService extends BaseService<Post, Long> {

    void create(Post post, User user);
}
