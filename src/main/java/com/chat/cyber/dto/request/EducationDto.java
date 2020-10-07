package com.chat.cyber.dto.request;

import com.chat.cyber.model.enums.EducationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationDto {
    private Long id;
    private EducationType educationType;
    private Long countryCode;
    private Long cityCode;
    private Long schoolNameCode;
    private String startDate;
    private String endDate;
    private String classType;
    private String specialization;
    private Long facultyCode;
    private Long studyTypeCode;
    private Long statusCode;
}
