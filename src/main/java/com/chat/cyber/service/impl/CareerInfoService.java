package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.dto.request.userinfo.CareerInfoDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.CareerInfo;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.User;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.CareerInfoRepo;
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
public class CareerInfoService implements AdditionalUserInfoService {

    @Autowired
    private CareerInfoRepo careerInfoRepo;
    @Autowired
    private RefsValuesService refsValuesService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    public void save(RefsCodeName refsCodeName, List<BaseUserInfoDto> userInfoDtos, Principal principal) {
        List<Long> savedIds = userInfoDtos.stream().map(BaseUserInfoDto::getId).collect(Collectors.toList());
        List<CareerInfo> careerInfos = userInfoDtos.stream().map(this::mapDto).collect(Collectors.toList());
        User user = userRepository.findByUuid(profileService.getUuid(principal)).orElse(null);
        careerInfoRepo.deleteOldCareerInfos(savedIds, user.getId());
        careerInfos.forEach(user::addCareerInfo);
        userRepository.save(user);
    }

    private CareerInfo mapDto(BaseUserInfoDto baseUserInfoDto) {
        CareerInfoDto careerInfoDto = (CareerInfoDto) baseUserInfoDto;
        CareerInfo careerInfo;
        RefsValues city = refsValuesService.findByIdAndRefsCodeName(careerInfoDto.getCityCode(), RefsCodeName.CITY).orElse(null);
        RefsValues country = refsValuesService.findByIdAndRefsCodeName(careerInfoDto.getCityCode(), RefsCodeName.COUNTRY).orElse(null);
        if (careerInfoDto.getId() == null) {
            careerInfo = new CareerInfo();
        } else {
            careerInfo = careerInfoRepo.findById(careerInfoDto.getId()).orElseThrow(RestException::new);
        }
        careerInfo.setCity(city);
        careerInfo.setCountry(country);
        careerInfo.setWorkPlaceTitle(careerInfoDto.getWorkPlaceTitle());
        careerInfo.setRoleTitle(careerInfoDto.getRoleTitle());
        careerInfo.setEndDate(careerInfoDto.getEndDate());
        careerInfo.setStartDate(careerInfoDto.getStartDate());
        return careerInfo;
    }
}
