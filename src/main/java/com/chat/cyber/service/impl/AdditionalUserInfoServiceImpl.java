package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.service.AdditionalUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Primary
public class AdditionalUserInfoServiceImpl implements AdditionalUserInfoService {

    @Autowired
    private InterestsService interestsService;
    @Autowired
    private ContactInfoService contactInfoService;
    @Autowired
    private CareerInfoService careerInfoService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private PersonalViewsService personalViewsService;

    @Override
    public void save(RefsCodeName refsCodeName, List<BaseUserInfoDto> userInfoDtos, Principal principal) {
        if (refsCodeName == null) {
            throw new RestException();
        }
        if (refsCodeName == RefsCodeName.INTERESTS) {
            interestsService.save(refsCodeName, userInfoDtos, principal);
        } else if (refsCodeName == RefsCodeName.CAREER_INFO) {
            careerInfoService.save(refsCodeName, userInfoDtos, principal);
        } else if (refsCodeName == RefsCodeName.CONTACT_INFO) {
            contactInfoService.save(refsCodeName, userInfoDtos, principal);
        } else if (refsCodeName == RefsCodeName.EDUCATION) {
            educationService.save(refsCodeName, userInfoDtos, principal);
        } else if (refsCodeName == RefsCodeName.PERSONAL_VIEWS) {
            personalViewsService.save(refsCodeName, userInfoDtos, principal);
        }
    }
}
