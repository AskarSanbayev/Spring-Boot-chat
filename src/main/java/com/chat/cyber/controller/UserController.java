package com.chat.cyber.controller;

import com.chat.cyber.dto.request.PostDto;
import com.chat.cyber.model.User;
import com.chat.cyber.service.UserService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @PreAuthorize("hasRole('ROLE_user')")
    @GetMapping
    public List<User> findAll(Principal principal) {
        return userService.findAll();
    }

    @GetMapping("/{uuid}/friends")
    public List<User> findAllFriends(@PathVariable("uuid") String uuid) {
        return userService.findByUUid(uuid).getFriendList();
    }
}
