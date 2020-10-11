package com.chat.cyber.controller;

import com.chat.cyber.model.User;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_user')")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{uuid}/friends")
    public List<User> findAllFriends(@PathVariable("uuid") String uuid) {
        return userService.findById(uuid).getFriendList();
    }
}
