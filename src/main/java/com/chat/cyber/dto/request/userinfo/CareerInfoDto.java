package com.chat.cyber.dto.request.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CareerInfoDto extends BaseUserInfoDto {
    private String workPlaceTitle;
    private Long countryCode;
    private Long cityCode;
    private String startDate;
    private String endDate;
    private String roleTitle;
}
