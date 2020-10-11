package com.chat.cyber.controller;

import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.service.PostService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Validated
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void createPost(String text, Principal principal) {
        postService.create(text, principal);
    }

    @PutMapping
    public void updatePost(@RequestBody PostDto post) {
        postService.update(post);
    }

    @DeleteMapping
    public void deletePost(String uuid) {
        postService.deleteById(uuid);
    }
}
