package com.chat.cyber.service;

import com.chat.cyber.model.Post;

import java.security.Principal;

public interface PostService extends BaseService<Post, Long> {

    void create(Post post, Principal principal);
}
