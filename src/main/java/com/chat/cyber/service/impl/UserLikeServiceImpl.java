package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.UserLikeDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.User;
import com.chat.cyber.model.UserLike;
import com.chat.cyber.model.enums.UserLikeType;
import com.chat.cyber.repo.UserLikeRepo;
import com.chat.cyber.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserLikeRepo userLikeRepo;

    @Override
    public void update(UserLikeDto userLikeDto, Principal principal) {
        if (userLikeDto.getUserLikeType() == null && (userLikeDto.getPostId() == null || userLikeDto.getCommentId() == null)) {
            throw new RestException();
        }
        updateLikeAndDisLike(userLikeDto.getPostId() != null, userLikeDto, principal);
    }

    // TODO
    // mb change to procedure
    private void updateLikeAndDisLike(boolean isPost, UserLikeDto userLikeDto, Principal principal) {
        String uuid = profileService.getUuid(principal);
        User user = userService.findByUUid(uuid).orElseThrow(RestException::new);
////        UserLike userLike = isPost ? postService.findById(userLikeDto.getPostId()).getUserLike()
////                : commentService.findById(userLikeDto.getCommentId()).getUserLike();
//        if (userLikeDto.getUserLikeType() == UserLikeType.LIKE) {
//            userLike.getAuthor().add(user);
//        } else {
//            userLike.getAuthor().remove(user);
//        }
//        userLike.setLikesCount((long) userLike.getAuthor().size());
//        userLikeRepo.save(userLike);
    }
}
