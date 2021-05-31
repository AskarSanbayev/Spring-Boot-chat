package com.chat.cyber.controller;

import com.chat.cyber.dto.request.CommentDto;
import com.chat.cyber.dto.request.UserLikeDto;
import com.chat.cyber.service.CommentService;
import com.chat.cyber.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserLikeService userLikeService;

    @Autowired
    public CommentController(CommentService commentService, UserLikeService userLikeService) {
        this.commentService = commentService;
        this.userLikeService = userLikeService;
    }

    @PostMapping
    public void createComment(@RequestBody CommentDto comment,
                              Principal principal) {
        commentService.create(comment, principal);
    }

    @PutMapping
    public void updateComment(@RequestBody CommentDto comment) {
        commentService.update(comment);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    @PutMapping("/like")
    public void updateLikeAndDislike(@ApiIgnore Principal principal, @RequestBody UserLikeDto userLikeDto) {
        userLikeService.update(userLikeDto, principal);
    }

}
