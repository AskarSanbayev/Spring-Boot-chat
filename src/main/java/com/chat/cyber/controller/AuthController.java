package com.chat.cyber.controller;

import com.chat.cyber.dto.AuthenticationRequestDto;
import com.chat.cyber.model.Token;
import com.chat.cyber.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.chat.cyber.util.AppConstants.*;

@RestController
@RequestMapping(value = AUTH)
public class AuthController {

    private final TokenService tokenService;

    @Autowired
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(LOGIN)
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        Token token = tokenService.create(requestDto.getLogin());
        return ResponseEntity.ok(token);
    }

    @PostMapping(REFRESH)
    public ResponseEntity refreshToken(@RequestBody Token token) {
        Optional<Token> updateToken = tokenService.update(token.getRefreshToken());
        return ResponseEntity.ok(updateToken);
    }
}
