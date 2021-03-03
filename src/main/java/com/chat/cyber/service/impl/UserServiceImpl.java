package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.RegUserDataDto;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUUid(String uuid) {
        return userRepository.findByUuid(uuid);
    }

    @Override
    public Optional<User> findByLogin(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void create(RegUserDataDto regUserDataDto) {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(regUserDataDto.getLogin());
        user.setEmail(regUserDataDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException();
    }
}