package com.chat.cyber.service;

import com.chat.cyber.model.Token;
import com.chat.cyber.model.User;

import java.util.Optional;

public interface TokenService extends BaseService<Token, Long> {

    Optional<Token> update(String refreshToken);

    Token create(String userLogin);

    void deleteByUserId(Long id);
}
