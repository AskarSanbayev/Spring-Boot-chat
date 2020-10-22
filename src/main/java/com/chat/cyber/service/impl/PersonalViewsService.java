package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.dto.request.userinfo.PersonalViewsDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.PersonalViews;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.PersonalViewsRepo;
import com.chat.cyber.service.AdditionalUserInfoService;
import com.chat.cyber.service.RefsValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class PersonalViewsService implements AdditionalUserInfoService {

    @Autowired
    private PersonalViewsRepo personalViewsRepo;
    @Autowired
    private RefsValuesService refsValuesService;

    public void save(RefsCodeName refsCodeName, List<BaseUserInfoDto> userInfoDtos, Principal principal) {
        if (!userInfoDtos.isEmpty() && userInfoDtos.get(0).getId() != null) {
            PersonalViewsDto personalViewsDto = (PersonalViewsDto) userInfoDtos.get(0);
            RefsValues personalPriority = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getPersonalPriorityCode(), RefsCodeName.PERSONAL_PRIORITY).orElse(null);
            RefsValues politicalView = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getPoliticalViewsCode(), RefsCodeName.POLITICAL_VIEWS).orElse(null);
            RefsValues importantInOthers = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getImportantInOthersCode(), RefsCodeName.IMPORTANT_IN_OTHERS).orElse(null);
            RefsValues religion = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getReligionCode(), RefsCodeName.RELIGION).orElse(null);
            RefsValues alcoholView = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getViewsOnAlcoholCode(), RefsCodeName.VIEWS_ON_ALCOHOL).orElse(null);
            RefsValues smokingView = refsValuesService.findByIdAndRefsCodeName(personalViewsDto.getViewsOnSmokingCode(), RefsCodeName.VIEWS_ON_SMOKING).orElse(null);
            PersonalViews personalViews = personalViewsRepo.findById(personalViewsDto.getId()).orElseThrow(RestException::new);
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
}
