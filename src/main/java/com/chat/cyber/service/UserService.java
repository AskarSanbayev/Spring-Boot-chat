package com.chat.cyber.service;

import com.chat.cyber.dto.PageInfoDto;
import com.chat.cyber.dto.request.RegUserDataDto;
import com.chat.cyber.dto.response.PagePresentDto;
import com.chat.cyber.dto.response.UserDto;
import com.chat.cyber.model.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    Optional<User> findByUUid(String uuid);

    void editFriends(Principal principal, Long friendId, String friendUuid, boolean isRemove);

    void create(RegUserDataDto regUserDataDto);

    List<User> findAll();

    void deleteById(String id);

    User findByIdAndUuid(Long id, String uuid);

    PagePresentDto<UserDto> getFriends(Principal principal, PageInfoDto pageInfo);

}
