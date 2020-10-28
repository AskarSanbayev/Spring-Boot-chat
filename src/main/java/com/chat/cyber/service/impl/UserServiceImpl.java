package com.chat.cyber.service.impl;

import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.ContactInfo;
import com.chat.cyber.model.Interests;
import com.chat.cyber.model.PersonalViews;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.UserService;
import org.keycloak.representations.IDToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileService profileService;

    public UserServiceImpl(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUUid(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(RestException::new);
    }

    @Override
    public User findByLogin(String username) {
        return userRepository.findByUsername(username).orElseThrow(RestException::new);
    }

    @Override
    public void create(IDToken idToken) {
        String uuid = idToken.getSubject();
        Optional<User> user = userRepository.findByUuid(uuid);
        if (!user.isPresent()) {
            User newUser = new User();
            newUser.setUuid(idToken.getSubject());
            newUser.setName(idToken.getGivenName());
            newUser.setLastName(idToken.getFamilyName());
            newUser.setUsername(idToken.getPreferredUsername());
            newUser.setEmail(idToken.getEmail());
            ContactInfo userContactInfo = new ContactInfo();
            Interests userInterests = new Interests();
            PersonalViews personalViews = new PersonalViews();
            newUser.setUserContactInfo(userContactInfo);
            newUser.setUserInterests(userInterests);
            newUser.setPersonalViews(personalViews);
            userContactInfo.setUser(newUser);
            userInterests.setUser(newUser);
            personalViews.setUser(newUser);
            userRepository.save(newUser);
        }
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException();
    }
}