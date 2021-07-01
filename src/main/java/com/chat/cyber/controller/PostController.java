package com.chat.cyber.controller;

import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.dto.request.UserLikeDto;
import com.chat.cyber.service.PostService;
import com.chat.cyber.service.UserLikeService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/post")
public class PostController {

    private final PostService postService;
    private final UserLikeService userLikeService;

    public PostController(PostService postService, UserLikeService userLikeService) {
        this.postService = postService;
        this.userLikeService = userLikeService;
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

    @PutMapping("/like")
    public void updateLikeAndDislike(@ApiIgnore Principal principal, @RequestBody UserLikeDto userLikeDto) {
        userLikeService.update(userLikeDto, principal);
    }
}
