package com.chat.cyber.service;

import com.chat.cyber.dto.request.RegUserDataDto;

import java.util.Locale;

public interface RegistrationService {

    void registerUser(RegUserDataDto regUserDataDto, Locale locale);
}
