package com.chat.cyber.service;

import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.model.Post;

import java.security.Principal;
import java.util.List;

public interface PostService {

    void create(PostDto postDto, Principal principal);

    List<Post> findAllByAuthor(Principal principal);

    void likePost(Principal principal, Long postId);

    void update(Principal principal, PostDto postDto);

    List<Post> findAll();

    void deleteById(Principal principal, Long postId);

    Post findById(Long id);
}
