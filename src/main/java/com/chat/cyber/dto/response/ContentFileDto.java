package com.chat.cyber.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentFileDto {

    private String fileName;
    private String fileType;
    private byte[] data;

}
