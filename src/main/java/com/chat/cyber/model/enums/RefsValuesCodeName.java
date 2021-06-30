package com.chat.cyber.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
public enum RefsValuesCodeName {

    CONTENT_VIDEO("video"),
    CONTENT_IMAGE("image"),
    CONTENT_PROFILE_IMAGE("profile_image");

    @Getter
    private String value;

    public static RefsValuesCodeName of(String value) {
        if (value == null)
            return null;

        return Stream.of(values()).filter(item -> item.value.equals(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
