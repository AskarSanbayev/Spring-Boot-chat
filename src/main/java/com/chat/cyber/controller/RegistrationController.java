package com.chat.cyber.controller;

import com.chat.cyber.dto.request.RegUserDataDto;
import com.chat.cyber.service.RegistrationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Locale;

@Api(value = "Регистрация пользователя", tags = {"Регистрация пользователя"})
@RestController
@RequestMapping("/api/user/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public void register(@RequestBody RegUserDataDto regUserDataDto,
                         @ApiIgnore Locale locale) {
        registrationService.registerUser(regUserDataDto, locale);
    }
}
