package com.chat.cyber.service;

import com.chat.cyber.security.CustomUserDetails;

import java.security.Principal;
import java.util.Collection;

public interface ProfileService {
    String getUuid(Principal principal);

    Long getId(Principal principal);

    String getUsername(Principal principal);

    String getEmail(Principal principal);

    CustomUserDetails getUserDetails(Principal principal);

    Collection<String> getUserRoles(Principal principal);
}
