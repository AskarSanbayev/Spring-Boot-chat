package com.chat.cyber.service;

import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.model.Post;

import java.security.Principal;
import java.util.List;

public interface PostService extends BaseService<Post, String> {

    void create(String text, Principal principal);

    List<Post> findAllByAuthor(Principal principal);

    void update(PostDto postDto);
}
