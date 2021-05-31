package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.dto.request.userinfo.EducationDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.Education;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.User;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.EducationRepo;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.AdditionalUserInfoService;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.RefsValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService implements AdditionalUserInfoService {

    @Autowired
    private EducationRepo educationRepo;
    @Autowired
    private RefsValuesService refsValuesService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(RefsCodeName refsCodeName, List<BaseUserInfoDto> userInfoDtos, Principal principal) {
        List<Long> savedIds = userInfoDtos.stream().map(BaseUserInfoDto::getId).collect(Collectors.toList());
        List<Education> educations = userInfoDtos.stream().map(this::mapDto).collect(Collectors.toList());
        User user = userRepository.findByUuid(profileService.getUuid(principal)).orElse(null);
        educationRepo.deleteOldCareerInfos(savedIds, user.getId());
        userRepository.save(user);
    }

    private Education mapDto(BaseUserInfoDto baseUserInfoDto) {
        EducationDto educationDto = (EducationDto) baseUserInfoDto;
        Education education;
        RefsValues city = refsValuesService.findByIdAndRefsCodeName(educationDto.getCityCode(), RefsCodeName.CITY).orElse(null);
        RefsValues country = refsValuesService.findByIdAndRefsCodeName(educationDto.getCityCode(), RefsCodeName.COUNTRY).orElse(null);
        RefsValues schoolName = refsValuesService.findByIdAndRefsCodeName(educationDto.getCityCode(), RefsCodeName.SCHOOL_NAME).orElse(null);
        RefsValues faculty = refsValuesService.findByIdAndRefsCodeName(educationDto.getCityCode(), RefsCodeName.FACULTY).orElse(null);
        RefsValues studyType = refsValuesService.findByIdAndRefsCodeName(educationDto.getCityCode(), RefsCodeName.STUDY_TYPE).orElse(null);
        RefsValues status = refsValuesService.findByIdAndRefsCodeName(educationDto.getCityCode(), RefsCodeName.SCHOOL_STATUS).orElse(null);
        if (educationDto.getId() == null) {
            education = new Education();
        } else {
            education = educationRepo.findById(educationDto.getId()).orElseThrow(RestException::new);
        }
        education.setEducationType(educationDto.getEducationType());
        education.setCity(city);
        education.setCountry(country);
        education.setSchoolName(schoolName);
        education.setFaculty(faculty);
        education.setStudyType(studyType);
        education.setStatus(status);
        education.setStartDate(educationDto.getStartDate());
        education.setEndDate(educationDto.getEndDate());
        education.setClassType(educationDto.getClassType());
        education.setSpecialization(educationDto.getSpecialization());
        return education;
    }
}
