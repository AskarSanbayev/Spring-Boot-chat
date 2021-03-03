package com.chat.cyber.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegUserDataDto {

    private String login;
    private String email;
    private String password;
}
