package com.chat.cyber.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.chat.cyber.comp.PermissionHelper;
import com.chat.cyber.dto.response.ContentFileDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.exception.UnexpectedException;
import com.chat.cyber.model.ContentFile;
import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.model.enums.RefsValuesCodeName;
import com.chat.cyber.repo.ContentFilesRepo;
import com.chat.cyber.service.ProfileService;
import com.chat.cyber.service.RefsValuesService;
import com.chat.cyber.service.StorageService;
import com.chat.cyber.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    private static final String[] ALLOWED_IMAGE_EXTENSIONS = new String[]{"jpg", "jpeg", "tif", "tiff", "png"};
    private static final String[] ALLOWED_VIDEO_EXTENSIONS = new String[]{"mp4", "mov", "avi", "wmv"};

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    private final ProfileService profileService;
    private final MessageSource messageSource;
    private final ContentFilesRepo filesRepo;
    private final AmazonS3 s3storage;
    private final RefsValuesService refsValuesService;
    private final PermissionHelper permissionHelper;

    public StorageServiceImpl(ProfileService profileService, MessageSource messageSource, ContentFilesRepo filesRepo,
                              AmazonS3 s3storage, RefsValuesService refsValuesService, PermissionHelper permissionHelper) {
        this.profileService = profileService;
        this.messageSource = messageSource;
        this.filesRepo = filesRepo;
        this.s3storage = s3storage;
        this.refsValuesService = refsValuesService;
        this.permissionHelper = permissionHelper;
    }

    @Override
    public void loadImage(MultipartFile file, Principal principal, Locale locale) {
        final Long authorId = profileService.getId(principal);
        checkFile(locale, file, ALLOWED_IMAGE_EXTENSIONS);
        RefsValues imageType = refsValuesService.findCodeNameAndRefsCodeName(RefsValuesCodeName.CONTENT_IMAGE, RefsCodeName.REF_CONTENT_TYPE).orElseThrow(UnexpectedException::new);
        saveFile(file, authorId, imageType);
    }

    @Override
    public void loadProfileImage(MultipartFile file, Principal principal, Locale locale) {
        final Long authorId = profileService.getId(principal);
        checkFile(locale, file, ALLOWED_IMAGE_EXTENSIONS);
        ContentFile profileImage = filesRepo.findByUserIdAndAndContentTypeCodeName(authorId, RefsValuesCodeName.CONTENT_PROFILE_IMAGE.getValue());
        if (profileImage == null) {
            RefsValues profileImageType = refsValuesService.findCodeNameAndRefsCodeName(RefsValuesCodeName.CONTENT_PROFILE_IMAGE, RefsCodeName.REF_CONTENT_TYPE).orElseThrow(UnexpectedException::new);
            saveFile(file, authorId, profileImageType);
        }
    }

    @Override
    public ContentFileDto downloadProfileImage(Principal principal) {
        final Long authorId = profileService.getId(principal);
        ContentFile profileImage = filesRepo.findByUserIdAndAndContentTypeCodeName(authorId, RefsValuesCodeName.CONTENT_PROFILE_IMAGE.getValue());
        if (profileImage == null) {
            return null;
        }
        byte[] contentData = extractDataFromStorage(profileImage.getPath());
        return new ContentFileDto(profileImage.getName(), profileImage.getMimeType(), contentData);
    }

    @Override
    public ContentFileDto downloadFile(String uuid, Locale locale) {
        Optional<ContentFile> optionalFile = filesRepo.findById(UUID.fromString(uuid));
        if (!optionalFile.isPresent()) {
            return null;
        }
        ContentFile file = optionalFile.get();
        byte[] contentData = extractDataFromStorage(file.getPath());
        return new ContentFileDto(file.getName(), file.getMimeType(), contentData);
    }

    @Override
    public void loadVideo(MultipartFile file, Principal principal, Locale locale) {
        final Long authorId = profileService.getId(principal);
        checkFile(locale, file, ALLOWED_VIDEO_EXTENSIONS);
        RefsValues videoType = refsValuesService.findCodeNameAndRefsCodeName(RefsValuesCodeName.CONTENT_VIDEO, RefsCodeName.REF_CONTENT_TYPE).orElseThrow(UnexpectedException::new);
        saveFile(file, authorId, videoType);
    }

    @Override
    public void deleteFile(Principal principal, String uuid, Locale locale) {
        final Long authorId = profileService.getId(principal);
        Optional<ContentFile> file = filesRepo.findById(UUID.fromString(uuid));
        if (!file.isPresent()) {
            throw new RestException(messageSource.getMessage("exception.rest.file_not_found",
                    null, locale));
        }
        permissionHelper.checkFileEditPermission(authorId, file.get());
        s3storage.deleteObject(bucketName, file.get().getPath());
        filesRepo.deleteById(UUID.fromString(uuid));
    }

    private byte[] extractDataFromStorage(String filePath) {
        S3Object object = s3storage.getObject(bucketName, filePath);
        byte[] contentData;
        try {
            contentData = IOUtils.toByteArray(object.getObjectContent());
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return contentData;
    }

    private void saveFile(MultipartFile file, Long userId, RefsValues contentType) {
        File imageFile;
        try {
            imageFile = FileUtils.convertMultipartToFile(file);
        } catch (IOException e) {
            throw new UnexpectedException();
        }
        UUID uuid = UUID.randomUUID();
        String path = FileUtils.generateFileName(uuid);
        ContentFile contentFile = new ContentFile();
        contentFile.setId(uuid);
        contentFile.setPath(path);
        contentFile.setMimeType(file.getContentType());
        contentFile.setName(file.getOriginalFilename());
        contentFile.setUserId(userId);
        contentFile.setContentType(contentType);
        uploadPublicFile(path, imageFile);
        filesRepo.save(contentFile);
    }

    private void checkFile(Locale locale, MultipartFile file, String[] allowedExtensions) {
        if (file.getSize() == 0) {
            throw new RestException(messageSource.getMessage("exception.rest.file_is_empty",
                    null, locale));
        } else if (!FileUtils.isFileWithOneOfExtensions(file, allowedExtensions)) {
            throw new RestException(messageSource.getMessage(
                    "exception.rest.files_with_allowed_ext", null, locale));
        }
    }

    private void uploadPublicFile(String fileName, File file) {
        s3storage.putObject(bucketName, fileName, file);
        file.delete();
    }
}
