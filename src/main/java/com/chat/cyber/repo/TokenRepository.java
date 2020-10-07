package com.chat.cyber.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteByUserId(Long id);

    Optional<Token> findByRefreshToken(String refreshToken);
}
