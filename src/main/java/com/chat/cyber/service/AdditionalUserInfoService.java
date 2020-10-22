package com.chat.cyber.service;

import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.model.enums.RefsCodeName;

import java.security.Principal;
import java.util.List;

public interface AdditionalUserInfoService {

    void save(RefsCodeName refsCodeName, List<BaseUserInfoDto> userInfoDtos, Principal principal);
}
