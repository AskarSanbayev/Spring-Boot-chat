package com.chat.cyber.converter;

import com.chat.cyber.dto.response.UserDto;
import com.chat.cyber.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        UserDto resp = new UserDto();
        resp.setId(user.getId());
        resp.setUuid(user.getUuid());
        resp.setAge(user.getAge());
        resp.setBirthDay(user.getBirthday());
        resp.setEmail(user.getEmail());
        resp.setFullName(user.getFullName());
        return resp;
    }
}
