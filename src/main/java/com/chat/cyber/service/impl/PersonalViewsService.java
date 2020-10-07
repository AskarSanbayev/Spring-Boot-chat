package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.PersonalViewsDto;
import com.chat.cyber.exception.EntityNotFoundException;
import com.chat.cyber.model.PersonalViews;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.User;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.PersonalViewsRepo;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.RefsValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class PersonalViewsService {

    @Autowired
    private PersonalViewsRepo personalViewsRepo;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private RefsValuesService refsValuesService;
    @Autowired
    private UserRepository userRepository;

    public void save(PersonalViewsDto personalViewsDto, Principal principal) {
        PersonalViews personalViews;
        RefsValues personalPriority = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getPersonalPriorityCode(), RefsCodeName.PERSONAL_PRIORITY).orElse(null);
        RefsValues politicalView = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getPoliticalViewsCode(), RefsCodeName.POLITICAL_VIEWS).orElse(null);
        RefsValues importantInOthers = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getImportantInOthersCode(), RefsCodeName.IMPORTANT_IN_OTHERS).orElse(null);
        RefsValues religion = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getReligionCode(), RefsCodeName.RELIGION).orElse(null);
        RefsValues alcoholView = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getViewsOnAlcoholCode(), RefsCodeName.VIEWS_ON_ALCOHOL).orElse(null);
        RefsValues smokingView = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getViewsOnSmokingCode(), RefsCodeName.VIEWS_ON_SMOKING).orElse(null);
        if (personalViewsDto.getId() == null) {
            personalViews = new PersonalViews();
        } else {
            personalViews = personalViewsRepo.findById(personalViewsDto.getId()).orElseThrow(EntityNotFoundException::new);
        }
        User user = userRepository.findByUuid(profileService.getUuid(principal)).orElse(null);
        personalViews.setUser(user);
        personalViews.setInspiredBy(personalViewsDto.getInspiredBy());
        personalViews.setPersonalPriority(personalPriority);
        personalViews.setPoliticalViews(politicalView);
        personalViews.setImportantInOthers(importantInOthers);
        personalViews.setReligion(religion);
        personalViews.setViewsOnAlcohol(alcoholView);
        personalViews.setViewsOnSmoking(smokingView);
        personalViewsRepo.save(personalViews);
    }
}
