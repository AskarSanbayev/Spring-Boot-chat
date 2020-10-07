package com.chat.cyber.service.impl;

import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByLogin(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException();
    }
}