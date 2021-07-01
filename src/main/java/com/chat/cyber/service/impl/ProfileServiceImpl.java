package com.chat.cyber.service.impl;

import com.chat.cyber.security.CustomUserDetails;
import com.chat.cyber.service.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Override
    public String getUuid(Principal principal) {
        return getUserDetails(principal).getUuid();
    }

    @Override
    public Long getId(Principal principal) {
        return getUserDetails(principal).getId();
    }

    @Override
    public String getUsername(Principal principal) {
        return getUserDetails(principal).getUsername();
    }

    @Override
    public String getEmail(Principal principal) {
        return getUserDetails(principal).getEmail();
    }

    @Override
    public CustomUserDetails getUserDetails(Principal principal) {
        if (principal == null) return null;
        return (CustomUserDetails) ((Authentication) principal).getPrincipal();
    }

    @Override
    public Collection<String> getUserRoles(Principal principal) {
        if (principal == null) {
            return Collections.singletonList("empty");
        } else {
            Set<String> roles = new HashSet<String>();
            Collection<? extends GrantedAuthority> authorities = ((Authentication) principal).getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                roles.add(grantedAuthority.getAuthority());
            }
            return roles;
        }
    }
}
