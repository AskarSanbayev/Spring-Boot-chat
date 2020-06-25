package com.chat.cyber.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String store(MultipartFile file, Long id);

    Resource loadAsResource(String filename);

    void deleteFile(String fileName);
}