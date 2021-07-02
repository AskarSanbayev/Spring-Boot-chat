package com.chat.cyber.controller;

import com.chat.cyber.dto.request.CommentDto;
import com.chat.cyber.service.CommentService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public void createComment(@RequestBody CommentDto comment,
                              Principal principal) {
        commentService.create(comment, principal);
    }

    @PutMapping
    public void updateComment(@ApiIgnore Principal principal, @RequestBody CommentDto comment) {
        commentService.update(principal, comment);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@ApiIgnore Principal principal, @PathVariable(name = "id") Long commentId) {
        commentService.deleteById(principal, commentId);
    }

    @PutMapping("/{id}/like")
    public void updateLikeAndDislike(@ApiIgnore Principal principal,  @PathVariable(name = "id") Long commentId) {
        commentService.likeComment(principal, commentId);
    }

}
