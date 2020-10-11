package com.chat.cyber.service;

import com.chat.cyber.model.User;

import java.util.List;

public interface UserService {

    User findByLogin(String login);

    List<User> findAll();

    void deleteById(String id);

    User findById(String id);

}
