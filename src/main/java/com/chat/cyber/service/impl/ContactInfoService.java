package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.dto.request.userinfo.ContactInfoDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.ContactInfo;
import com.chat.cyber.model.Interests;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.ContactInfoRepo;
import com.chat.cyber.service.AdditionalUserInfoService;
import com.chat.cyber.service.RefsValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoService implements AdditionalUserInfoService {

    @Autowired
    private ContactInfoRepo contactInfoRepo;
    @Autowired
    private RefsValuesService refsValuesService;

    @Override
    public void save(RefsCodeName refsCodeName, List<BaseUserInfoDto> userInfoDtos, Principal principal) {
        if (!userInfoDtos.isEmpty() && userInfoDtos.get(0).getId() != null) {
            ContactInfoDto contactInfoDto = (ContactInfoDto) userInfoDtos.get(0);
            RefsValues city = refsValuesService.findByIdAndRefsCodeName(contactInfoDto.getCityCode(), RefsCodeName.CITY).orElse(null);
            RefsValues country = refsValuesService.findByIdAndRefsCodeName(contactInfoDto.getCityCode(), RefsCodeName.COUNTRY).orElse(null);
            ContactInfo contactInfo = contactInfoRepo.findById(contactInfoDto.getId()).orElseThrow(RestException::new);
            contactInfo.setId(contactInfoDto.getId());
            contactInfo.setCity(city);
            contactInfo.setCountry(country);
            contactInfo.setFlat(contactInfoDto.getFlat());
            contactInfo.setStreet(contactInfoDto.getStreet());
            contactInfoRepo.save(contactInfo);
        }
    }
}
