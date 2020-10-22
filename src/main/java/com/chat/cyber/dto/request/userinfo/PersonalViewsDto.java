package com.chat.cyber.dto.request.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalViewsDto extends BaseUserInfoDto {

    private Long politicalViewsCode;
    private Long personalPriorityCode;
    private Long religionCode;
    private Long importantInOthersCode;
    private Long viewsOnSmokingCode;
    private Long viewsOnAlcoholCode;
    private String inspiredBy;
}
