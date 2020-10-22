package com.chat.cyber.controller;

import com.chat.cyber.dto.request.UserLikeDto;
import com.chat.cyber.model.User;
import com.chat.cyber.service.UserLikeService;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserLikeService userLikeService;

    @PreAuthorize("hasRole('ROLE_user')")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{uuid}/friends")
    public List<User> findAllFriends(@PathVariable("uuid") String uuid) {
        return userService.findByUUid(uuid).getFriendList();
    }

    @GetMapping("/{uuid}")
    public User findUser(@PathVariable("uuid") String uuid) {
        return userService.findByUUid(uuid);
    }

    @PutMapping("/like")
    public void findUser(@RequestBody UserLikeDto userLikeDto, Principal principal) {
        userLikeService.update(userLikeDto, principal);
    }
}
