package com.chat.cyber.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface StorageService {

    String store(MultipartFile file, Principal principal);

    Resource loadAsResource(String filename);

    void deleteFile(String fileName);
}