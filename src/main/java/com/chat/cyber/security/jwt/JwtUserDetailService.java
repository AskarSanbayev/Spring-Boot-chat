package com.chat.cyber.security.jwt;

import com.chat.cyber.model.User;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private UserService userService;
    private BCryptPasswordEncoder encoder;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        Optional<User> user = Optional.ofNullable(userService.findByLogin(login));
        if (user.isPresent()) {
            String password = user.get().getPassword();
            String encodedPassword = encoder.encode(password);
            user.get().setPassword(encodedPassword);
            return JwtUserFactory.create(user.get());
        }
        throw new UsernameNotFoundException("User with login not found");
    }
}
