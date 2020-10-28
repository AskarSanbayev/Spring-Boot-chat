package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.dto.request.userinfo.InterestsDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.Interests;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.InterestsRepo;
import com.chat.cyber.service.AdditionalUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class InterestsService implements AdditionalUserInfoService {

    @Autowired
    private InterestsRepo interestsRepo;

    public void save(RefsCodeName refsCodeName, List<BaseUserInfoDto> userInfoDtos, Principal principal) {
        if (!userInfoDtos.isEmpty() && userInfoDtos.get(0).getId() != null) {
            InterestsDto interestsDto = (InterestsDto) userInfoDtos.get(0);
            Interests interests = interestsRepo.findById(interestsDto.getId()).orElseThrow(RestException::new);
            interests.setAboutInterests(interestsDto.getAboutInterests());
            interests.setAboutMe(interestsDto.getAboutMe());
            interests.setActivities(interestsDto.getActivities());
            interests.setFavoriteBooks(interestsDto.getFavoriteBooks());
            interests.setFavoriteMovies(interestsDto.getFavoriteMovies());
            interests.setFavoriteMusic(interestsDto.getFavoriteMusic());
            interests.setFavoriteQuotes(interestsDto.getFavoriteQuotes());
            interests.setFavoriteShows(interestsDto.getFavoriteShows());
            interestsRepo.save(interests);
        }
    }
}
