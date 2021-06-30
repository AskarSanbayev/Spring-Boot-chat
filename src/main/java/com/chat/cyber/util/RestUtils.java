package com.chat.cyber.util;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class RestUtils {

    public static ResponseEntity<Object> buildResponseEntityWithInputStreamResource(
            String contentType, String title, byte[] fileData) {
        InputStreamResource inputStreamResource = new InputStreamResource(
                new ByteArrayInputStream(fileData));
        HttpHeaders headers = buildHttpHeadersOfResponseEntity(contentType, fileData.length, title);
        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
    }

    private static HttpHeaders buildHttpHeadersOfResponseEntity(String contentType, long contentLength,
                                                                String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(contentLength);
        headers.set("Content-Type", contentType);
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(title, StandardCharsets.UTF_8)
                .build();
        headers.setContentDisposition(contentDisposition);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return headers;
    }


}
