package com.chat.cyber.service.impl;

import com.chat.cyber.config.StorageProperties;
import com.chat.cyber.exception.UnexpectedException;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Objects;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final ProfileService profileService;

    @Autowired
    public FileSystemStorageService(StorageProperties properties, ProfileService profileService) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.profileService = profileService;
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new UnexpectedException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file, Principal principal) {
        Objects.requireNonNull(file);
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String uuid = profileService.getUuid(principal);
        if (file.isEmpty()) {
            throw new UnexpectedException("Failed to store empty file " + filename);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path targetLocation = this.rootLocation.resolve(uuid + ".png");
            Files.copy(inputStream, targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UnexpectedException("Failed to store file " + filename, e);
        }
        return filename;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new UnexpectedException("Could not read file: " + filename);
            }
        } catch (IOException e) {
            throw new UnexpectedException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            Path file = load(fileName);
            Files.delete(file);
        } catch (IOException e) {
            throw new UnexpectedException("Failed to delete file " + fileName, e);
        }
    }

    private Path load(String filename) {
        return rootLocation.resolve(filename);
    }
}
