package com.chat.cyber.controller;

import com.chat.cyber.dto.request.CommentDto;
import com.chat.cyber.model.Comment;
import com.chat.cyber.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Validated
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public void createComment(@RequestBody CommentDto comment,
                              Principal principal) {
        commentService.create(comment, principal);
    }

    @PutMapping
    public void updateComment(@RequestBody CommentDto comment) {
        commentService.update(comment);
    }

    @DeleteMapping
    public void deleteComment(@RequestBody Comment comment) {
        commentService.deleteById(comment.getId());
    }

}
