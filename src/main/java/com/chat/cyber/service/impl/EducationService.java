package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.EducationDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.Education;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.User;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.EducationRepo;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.RefsValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepo educationRepo;

    @Autowired
    private RefsValuesService refsValuesService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    public void save(List<EducationDto> educationDtos, Principal principal) {
        educationDtos.forEach(el -> mapDto(el, principal));
    }

    private void mapDto(EducationDto educationDto, Principal principal) {
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
        User user = userRepository.findByUuid(profileService.getUuid(principal)).orElse(null);
        education.setEducationType(educationDto.getEducationType());
        education.setUser(user);
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
        educationRepo.save(education);
    }
}
