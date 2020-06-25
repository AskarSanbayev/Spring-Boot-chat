package com.chat.cyber.service;

import com.chat.cyber.model.Post;
import com.chat.cyber.security.jwt.JwtUser;

public interface PostService extends BaseService<Post, Long> {

    void create(Post post, JwtUser jwtUser);
}
