package com.chat.cyber.service.impl;

import com.chat.cyber.comp.PermissionHelper;
import com.chat.cyber.dto.request.CommentDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.exception.UnexpectedException;
import com.chat.cyber.model.Comment;
import com.chat.cyber.model.Post;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.CommentRepository;
import com.chat.cyber.service.CommentService;
import com.chat.cyber.service.PostService;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final ProfileService profileService;
    private final PermissionHelper permissionHelper;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(Principal principal, Long commentId) {
        final Long authorId = profileService.getId(principal);
        Comment comment = commentRepository.findById(commentId).orElseThrow(RestException::new);
        permissionHelper.checkCommentEditPermission(authorId, comment);
        commentRepository.deleteById(commentId);
    }

    @Override
    public void update(Principal principal, CommentDto commentDto) {
        final Long authorId = profileService.getId(principal);
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(RestException::new);
        Date updateDate = new Date();
        comment.setText(commentDto.getText());
        comment.setLastModifiedDate(updateDate);
        permissionHelper.checkCommentEditPermission(authorId, comment);
        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void likeComment(Principal principal, Long commentId) {
        final User user = userService.findByLogin(principal.getName()).orElseThrow(UnexpectedException::new);
        final Comment likedComment = findById(commentId);
        if (likedComment.getLikes().contains(user)) {
            likedComment.getLikes().remove(user);
        } else {
            likedComment.getLikes().add(user);
        }
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(RestException::new);
    }

    @Override
    public void create(CommentDto commentDto, Principal principal) {
        final Long authorId = profileService.getId(principal);
        Post post = postService.findById(commentDto.getPostId());
        Comment comment = new Comment();
        Date createDate = new Date();
        comment.setCreationDate(createDate);
        comment.setLastModifiedDate(createDate);
        comment.setAuthorId(authorId);
        comment.setPost(post);
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
    }
}
