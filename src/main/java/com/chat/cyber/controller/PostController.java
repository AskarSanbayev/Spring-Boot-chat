package com.chat.cyber.controller;

import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.service.PostService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void createPost(@ApiIgnore Principal principal, @RequestBody PostDto postDto) {
        postService.create(postDto, principal);
    }

    @PutMapping
    public void updatePost(@ApiIgnore Principal principal, @RequestBody PostDto post) {
        postService.update(principal, post);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@ApiIgnore Principal principal, @PathVariable Long id) {
        postService.deleteById(principal, id);
    }

    @PutMapping("/{id}/like")
    public void updateLikeAndDislike(@ApiIgnore Principal principal,
                                     @PathVariable(name = "id") Long postId) {
        postService.likePost(principal, postId);
    }
}
