package com.chat.cyber.service;

import com.chat.cyber.dto.PageInfoDto;
import com.chat.cyber.dto.request.RegUserDataDto;
import com.chat.cyber.model.User;
import org.springframework.data.domain.Slice;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    void editFriends(Principal principal, Long friendId, String friendUuid, boolean isRemove);

    void create(RegUserDataDto regUserDataDto);

    List<User> findAll();

    void deleteById(String id);

    Optional<User> findByUUid(String uuid);

    Slice<User> getFriends(Principal principal, PageInfoDto pageInfo);

}
