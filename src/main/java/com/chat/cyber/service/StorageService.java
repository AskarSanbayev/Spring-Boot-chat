package com.chat.cyber.service;

import com.chat.cyber.dto.response.ContentFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Locale;

public interface StorageService {

    void loadImage(MultipartFile file, Principal principal, Locale locale);

    void loadProfileImage(MultipartFile file, Principal principal, Locale locale);

    ContentFileDto downloadProfileImage(Principal principal);

    ContentFileDto downloadFile(String uuid, Locale locale);

    void loadVideo(MultipartFile file, Principal principal, Locale locale);

    void deleteFile(String uuid, Locale locale);
}