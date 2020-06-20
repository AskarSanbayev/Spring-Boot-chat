package com.chat.cyber.service.impl;

import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.Token;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.TokenRepository;
import com.chat.cyber.security.jwt.JwtTokenProvider;
import com.chat.cyber.service.TokenService;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository,
                            UserService userService,
                            AuthenticationManager authenticationManager,
                            JwtTokenProvider jwtTokenProvider) {
        this.tokenRepository = tokenRepository;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Token> findAll() {
        return tokenRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (tokenRepository.findById(id).isPresent()) {
            tokenRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Token not found");
        }
    }

    @Override
    public Optional<Token> update(String refreshToken) {
        boolean validated = jwtTokenProvider.validateToken(refreshToken);
        Optional<Token> token = validated ? tokenRepository.findByRefreshToken(refreshToken) : Optional.empty();
        if (token.isEmpty()) {
            throw new BadCredentialsException("Invalid refresh token");
        }
        String userLogin = token.get().getUser().getLogin();
        token = Optional.ofNullable(create(userLogin));
        return token;
    }

    @Override
    public Token findById(Long id) {
        return tokenRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Token create(String userLogin) {
        Token token = generateToken(userLogin);
        tokenRepository.save(token);
        return token;
    }

    @Override
    public void deleteByUserId(Long id) {
        tokenRepository.deleteByUserId(id);
    }

    private Token generateToken(String login) {
        User user = userService.findByLogin(login);
        deleteByUserId(user.getId());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, user.getPassword()));
        String accessToken = jwtTokenProvider.generateAccessToken(login, user.getRoles());
        String refreshToken = jwtTokenProvider.generateRefreshToken(login, user.getRoles());
        Token token = new Token();
        token.setUser(user);
        token.setRefreshToken(refreshToken);
        token.setAccessToken(accessToken);
        return token;
    }

    @Override
    public void create(Token token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Token token) {
        throw new UnsupportedOperationException();
    }
}
