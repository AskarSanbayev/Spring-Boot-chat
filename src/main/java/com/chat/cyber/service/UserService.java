package com.chat.cyber.service;

import com.chat.cyber.model.User;
import org.keycloak.representations.IDToken;

import java.security.Principal;
import java.util.List;

public interface UserService {

    User findByLogin(String login);

    void create(IDToken idToken);

    List<User> findAll();

    void deleteById(String id);

    User findByUUid(String uuid);

}
