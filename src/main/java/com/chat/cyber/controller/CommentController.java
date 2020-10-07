//package com.chat.cyber.controller;
//
//import com.chat.cyber.model.Comment;
//import com.chat.cyber.model.User;
//import com.chat.cyber.security.jwt.JwtUser;
//import com.chat.cyber.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Validated
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @Autowired
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @PostMapping("/{postId}")
//    public void createComment(@PathVariable("postId") Long postId, @RequestBody Comment comment,
//                              @AuthenticationPrincipal JwtUser user) {
//        commentService.create(comment, postId, user);
//    }
//
//    @PutMapping
//    public void updateComment(@RequestBody Comment comment) {
//        commentService.update(comment);
//    }
//
//    @DeleteMapping
//    public void deleteComment(@RequestBody Comment comment) {
//        commentService.deleteById(comment.getId());
//    }
//
//}
