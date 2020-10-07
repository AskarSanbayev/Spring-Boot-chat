package com.chat.cyber.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
public enum RefsCodeName {
    COUNTRY("COUNTRY"),
    REGIONS("REGIONS"),
    DISTRICTS("DISTRICTS"),
    CITY("CITY"),
    POLITICAL_VIEWS("POLITICAL_VIEWS"),
    PERSONAL_PRIORITY("PERSONAL_PRIORITY"),
    RELIGION("RELIGION"),
    IMPORTANT_IN_OTHERS("IMPORTANT_IN_OTHERS"),
    VIEWS_ON_SMOKING("VIEWS_ON_SMOKING"),
    VIEWS_ON_ALCOHOL("VIEWS_ON_ALCOHOL"),
    SCHOOL_NAME("SCHOOL_NAME"),
    STUDY_TYPE("STUDY_TYPE"),
    FACULTY("FACULTY"),
    SCHOOL_STATUS("SCHOOL_STATUS");


    @Getter
    private String value;

    public static RefsCodeName of(String value) {
        if (value == null)
            return null;

        return Stream.of(values()).filter(item -> item.value.equals(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
