package com.chat.cyber.dto.request.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactInfoDto extends BaseUserInfoDto{

    private Long countryCode;
    private Long cityCode;
    private String street;
    private String flat;
}
