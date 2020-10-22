package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.CareerInfoDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.model.CareerInfo;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.User;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.repo.CareerInfoRepo;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.RefsValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CareerInfoService {

    @Autowired
    private CareerInfoRepo careerInfoRepo;
    @Autowired
    private RefsValuesService refsValuesService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    public void save(List<CareerInfoDto> careerInfos, Principal principal) {
        careerInfos.forEach(el -> mapDto(el, principal));
    }

    private void mapDto(CareerInfoDto careerInfoDto, Principal principal) {
        CareerInfo careerInfo;
        RefsValues city = refsValuesService.findByIdAndRefsCodeName(careerInfoDto.getCityCode(), RefsCodeName.CITY).orElse(null);
        RefsValues country = refsValuesService.findByIdAndRefsCodeName(careerInfoDto.getCityCode(), RefsCodeName.COUNTRY).orElse(null);
        if (careerInfoDto.getId() == null) {
            careerInfo = new CareerInfo();
        } else {
            careerInfo = careerInfoRepo.findById(careerInfoDto.getId()).orElseThrow(RestException::new);
        }
        User user = userRepository.findByUuid(profileService.getUuid(principal)).orElse(null);
        careerInfo.setCity(city);
        careerInfo.setCity(country);
        careerInfo.setWorkPlaceTitle(careerInfoDto.getWorkPlaceTitle());
        careerInfo.setRoleTitle(careerInfoDto.getRoleTitle());
        careerInfo.setEndDate(careerInfoDto.getEndDate());
        careerInfo.setUser(user);
        careerInfo.setStartDate(careerInfoDto.getStartDate());
        careerInfoRepo.save(careerInfo);
    }
}
