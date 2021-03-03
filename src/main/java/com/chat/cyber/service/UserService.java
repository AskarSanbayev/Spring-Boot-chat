package com.chat.cyber.service;

import com.chat.cyber.dto.request.RegUserDataDto;
import com.chat.cyber.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    void create(RegUserDataDto regUserDataDto);

    List<User> findAll();

    void deleteById(String id);

    Optional<User> findByUUid(String uuid);

}
