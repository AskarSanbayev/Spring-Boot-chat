package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.ContactInfoDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.ContactInfo;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.User;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.ContactInfoRepo;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.RefsValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepo contactInfoRepo;
    @Autowired
    private RefsValuesService refsValuesService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    public void save(ContactInfoDto contactInfoDto, Principal principal) {
        ContactInfo contactInfo;
        RefsValues city = refsValuesService.findByIdAndRefsCodeName(contactInfoDto.getCityCode(), RefsCodeName.CITY).orElse(null);
        RefsValues country = refsValuesService.findByIdAndRefsCodeName(contactInfoDto.getCityCode(), RefsCodeName.COUNTRY).orElse(null);
        if (contactInfoDto.getId() == null) {
            contactInfo = new ContactInfo();
        } else {
            contactInfo = contactInfoRepo.findById(contactInfoDto.getId()).orElseThrow(RestException::new);
        }
        User user = userRepository.findByUuid(profileService.getUuid(principal)).orElse(null);
        contactInfo.setCity(city);
        contactInfo.setCountry(country);
        contactInfo.setFlat(contactInfoDto.getFlat());
        contactInfo.setStreet(contactInfoDto.getStreet());
        contactInfo.setUser(user);
        contactInfoRepo.save(contactInfo);
    }
}
