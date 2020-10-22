package com.chat.cyber.controller;

import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void createPost(Principal principal, @RequestBody PostDto postDto) {
        postService.create(postDto, principal);
    }

    @PutMapping
    public void updatePost(@RequestBody PostDto post) {
        postService.update(post);
    }

    @DeleteMapping(value = "/{uuid}")
    public void deletePost(@PathVariable String uuid) {
        postService.deleteById(uuid);
    }
}
