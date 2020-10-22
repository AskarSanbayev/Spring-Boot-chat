package com.chat.cyber.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
public enum UserLikeType {
    LIKE("LIKE"),
    DISLIKE("DISLIKE");

    @Getter
    private String value;

    public static UserLikeType of(String value) {
        if (value == null)
            return null;

        return Stream.of(values()).filter(item -> item.value.equals(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
