package com.chat.cyber.service;

import com.chat.cyber.model.User;

public interface UserService extends BaseService<User, Long> {

    User findByLogin(String login);
}
