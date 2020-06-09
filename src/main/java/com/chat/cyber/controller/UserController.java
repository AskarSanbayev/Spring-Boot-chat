package com.chat.cyber.controller;

import com.chat.cyber.model.User;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    private final UserService userService;
    // TODO : dto, pathes
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}/friends")
    public Set<User> findAllFriends(@PathVariable("id") Long id) {
        return userService.findById(id).getFriendList();
    }

    @GetMapping("/{login}")
    public User findByLogin(@PathVariable("login") String login) {
        return userService.findByLogin(login);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

}
