package com.chat.cyber.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class FileUtils {

    public static boolean isFileWithOneOfExtensions(MultipartFile file, String[] extensions) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName))
            return false;

        fileName = fileName.trim();
        int lastPointIndex = fileName.lastIndexOf('.');
        if (lastPointIndex == -1 || lastPointIndex == fileName.length() - 1)
            return false;

        String fileExt = fileName.substring(lastPointIndex + 1);
        return Arrays.stream(extensions).anyMatch(item -> item.equalsIgnoreCase(fileExt));
    }

    public static File convertMultipartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return convertedFile;
    }

    public static String generateFileName(UUID fileUuid) {
        return new Date().getTime() + "-" + fileUuid;
    }
}

