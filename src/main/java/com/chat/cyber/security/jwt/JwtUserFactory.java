package com.chat.cyber.security.jwt;

import com.chat.cyber.model.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getRoles()
        );
    }
}
