package com.chat.cyber.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactInfoDto {

    private Long id;
    private Long countryCode;
    private Long cityCode;
    private String street;
    private String flat;
}
