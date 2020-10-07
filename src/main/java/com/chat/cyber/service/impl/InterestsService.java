package com.chat.cyber.service.impl;

import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.Interests;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.InterestsRepo;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class InterestsService {

    @Autowired
    private InterestsRepo interestsRepo;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    public void save(Interests interests, Principal principal) {
        Interests interestSave;
        if (interests.getId() == null) {
            interestSave = new Interests();
        } else {
            interestSave = interestsRepo.findById(interests.getId()).orElseThrow(EntityNotFoundException::new);
        }
        User user = userRepository.findByUuid(profileService.getUuid(principal)).orElse(null);
        interestSave.setUser(user);
        interestSave.setAboutInterests(interests.getAboutInterests());
        interestSave.setAboutMe(interests.getAboutMe());
        interestSave.setActivities(interests.getActivities());
        interestSave.setFavoriteBooks(interests.getFavoriteBooks());
        interestSave.setFavoriteMovies(interests.getFavoriteMovies());
        interestSave.setFavoriteMusic(interests.getFavoriteMusic());
        interestSave.setFavoriteQuotes(interests.getFavoriteQuotes());
        interestSave.setFavoriteShows(interests.getFavoriteShows());
        interestsRepo.save(interestSave);
    }
}
