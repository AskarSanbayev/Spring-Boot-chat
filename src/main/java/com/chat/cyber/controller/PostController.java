package com.chat.cyber.controller;

import com.chat.cyber.model.Post;
import com.chat.cyber.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Validated
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void createPost(@RequestBody Post post, @AuthenticationPrincipal Principal principal) {
        postService.create(post, principal);
    }

    @PutMapping
    public void updatePost(@RequestBody Post post) {
        postService.update(post);
    }

    @DeleteMapping
    public void deletePost(@RequestBody Post post) {
        postService.deleteById(post.getId());
    }
}
