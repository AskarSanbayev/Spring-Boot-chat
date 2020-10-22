package com.chat.cyber.dto.request.userinfo;

import com.chat.cyber.model.ContactInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CareerInfoDto.class, name = "CAREER_INFO"),
        @JsonSubTypes.Type(value = ContactInfo.class, name = "CONTACT_INFO"),
        @JsonSubTypes.Type(value = EducationDto.class, name = "EDUCATION"),
        @JsonSubTypes.Type(value = InterestsDto.class, name = "INTERESTS"),
        @JsonSubTypes.Type(value = PersonalViewsDto.class, name = "PERSONAL_VIEWS")
})
public class BaseUserInfoDto {
    private Long id;
}
