package com.chat.cyber.service;

import com.chat.cyber.dto.request.UserLikeDto;

import java.security.Principal;

public interface UserLikeService {

    void update(UserLikeDto userLikeDto, Principal principal);
}
