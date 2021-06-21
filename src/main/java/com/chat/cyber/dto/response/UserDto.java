package com.chat.cyber.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String uuid;
    private String fullName;
    private String email;
    private int age;
    private Date birthDay;

}
