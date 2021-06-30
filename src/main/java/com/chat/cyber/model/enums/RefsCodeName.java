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
    PERSONAL_VIEWS("PERSONAL_VIEWS"),
    PERSONAL_PRIORITY("PERSONAL_PRIORITY"),
    RELIGION("RELIGION"),
    INTERESTS("INTERESTS"),
    EDUCATION("EDUCATION"),
    CAREER_INFO("CAREER_INFO"),
    CONTACT_INFO("CONTACT_INFO"),
    IMPORTANT_IN_OTHERS("IMPORTANT_IN_OTHERS"),
    VIEWS_ON_SMOKING("VIEWS_ON_SMOKING"),
    VIEWS_ON_ALCOHOL("VIEWS_ON_ALCOHOL"),
    SCHOOL_NAME("SCHOOL_NAME"),
    STUDY_TYPE("STUDY_TYPE"),
    FACULTY("FACULTY"),
    SCHOOL_STATUS("SCHOOL_STATUS"),
    REF_LANGUAGES("ref_languages"),
    REF_FACULTY("ref_faculty"),
    REF_STUDY_TYPE("ref_study_type"),
    REF_EDUCATION_STATUS("ref_education_status"),
    REF_CONTENT_TYPE("ref_content_type");

    @Getter
    private String value;

    public static RefsCodeName of(String value) {
        if (value == null)
            return null;

        return Stream.of(values()).filter(item -> item.value.equals(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
