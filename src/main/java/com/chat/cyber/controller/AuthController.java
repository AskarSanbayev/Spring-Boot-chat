package com.chat.cyber.controller;

import com.chat.cyber.dto.AuthenticationRequestDto;
import com.chat.cyber.model.User;
import com.chat.cyber.security.jwt.JwtTokenProvider;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.chat.cyber.util.AppConstants.*;

@RestController
@RequestMapping(value = AUTH)
public class AuthController {

    private static final String INVALID_USERNAME_OR_PASSWORD = "Invalid username or password";
    private static final String USERNAME_KEY = "username";
    private static final String TOKEN_KEY = "token";

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private Map<String, String> tokens = new HashMap<>();

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    // TODO : change refreshToken to cookie impementation,
    @PostMapping(LOGIN)
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String login = requestDto.getLogin();
            Map<Object, Object> response = getResponse(login);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD);
        }
    }

    @PostMapping(REFRESH)
    public ResponseEntity refreshToken(@RequestHeader(AUTHORIZATION_HEADER) String accessToken) {
        try {
            String login = jwtTokenProvider.getUsername(accessToken);
            tokens.remove(accessToken);
            Map<Object, Object> response = getResponse(login);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD);
        }
    }

    private Map<Object, Object> getResponse(String login) {
        User user = userService.findByLogin(login);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, user.getPassword()));
        String newToken = jwtTokenProvider.generateAccessToken(login, user.getRoles());
        String refreshToken = jwtTokenProvider.generateRefreshToken(login, user.getRoles());
        tokens.put(newToken, refreshToken);
        Map<Object, Object> response = new HashMap<>();
        response.put(USERNAME_KEY, login);
        response.put(TOKEN_KEY, newToken);
        return response;
    }
}
