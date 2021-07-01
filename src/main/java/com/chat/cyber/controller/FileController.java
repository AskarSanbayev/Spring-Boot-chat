package com.chat.cyber.controller;

import com.chat.cyber.dto.response.ContentFileDto;
import com.chat.cyber.service.StorageService;
import com.chat.cyber.util.RestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Locale;

@RestController
@RequestMapping("/api/user/file")
@Api(value = "Загузка медиа", tags = {"Загузка медиа"})
public class FileController {

    private final StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ApiImplicitParam(paramType = "header", name = "Accept-Language", defaultValue = "ru")
    @PostMapping("/image")
    @ApiOperation(value = "Загрузка фото", notes = "Загрузка фото")
    public void loadImage(@ApiIgnore Locale locale,
                          @ApiIgnore Principal principal,
                          @RequestParam MultipartFile file) {
        storageService.loadImage(file, principal, locale);
    }

    @ApiImplicitParam(paramType = "header", name = "Accept-Language", defaultValue = "ru")
    @PostMapping("/profile-image")
    @ApiOperation(value = "Загрузка фото профиля", notes = "Загрузка фото профиля")
    public void loadProfileImage(@ApiIgnore Locale locale,
                                 @ApiIgnore Principal principal,
                                 @RequestParam MultipartFile file) {
        storageService.loadProfileImage(file, principal, locale);
    }

    @ApiImplicitParam(paramType = "header", name = "Accept-Language", defaultValue = "ru")
    @GetMapping("/profile-image")
    @ApiOperation(value = "Получение фото профиля", notes = "Получение фото профиля")
    public ResponseEntity<Object> downloadProfileImage(@ApiIgnore Locale locale,
                                                       @ApiIgnore Principal principal) {
        ContentFileDto contentFileDto = storageService.downloadProfileImage(principal);
        if (contentFileDto == null) {
            return null;
        }
        return RestUtils.buildResponseEntityWithInputStreamResource(contentFileDto.getFileType(), contentFileDto.getFileName(), contentFileDto.getData());
    }

    @ApiImplicitParam(paramType = "header", name = "Accept-Language", defaultValue = "ru")
    @PostMapping("/video")
    @ApiOperation(value = "Загрузка видео", notes = "Загрузка видео")
    public void loadVideo(@ApiIgnore Locale locale,
                          @ApiIgnore Principal principal,
                          @RequestParam MultipartFile file) {
        storageService.loadVideo(file, principal, locale);
    }

    @GetMapping
    public ResponseEntity<Object> downloadFile(@ApiIgnore Locale locale,
                                               @RequestParam String uuid) {
        ContentFileDto contentFileDto = storageService.downloadFile(uuid, locale);
        if (contentFileDto == null) {
            return null;
        }
        return RestUtils.buildResponseEntityWithInputStreamResource(contentFileDto.getFileType(), contentFileDto.getFileName(), contentFileDto.getData());
    }

    @DeleteMapping
    public void deleteFile(@ApiIgnore Principal principal, @ApiIgnore Locale locale, @RequestParam String uuid) {
        storageService.deleteFile(principal, uuid, locale);
    }
}
