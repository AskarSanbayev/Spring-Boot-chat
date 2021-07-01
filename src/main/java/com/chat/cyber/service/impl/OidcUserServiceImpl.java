package com.chat.cyber.service.impl;

import com.chat.cyber.exception.UnexpectedException;
import com.chat.cyber.model.User;
import com.chat.cyber.security.CustomOidcUserDetailsImpl;
import com.chat.cyber.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class OidcUserServiceImpl implements OAuth2UserService<OidcUserRequest, OidcUser> {

    private final String REQUIRED_ROLE_NAME_BEGIN = "ROLE_";

    private final UserService userService;
    private OidcUserService delegate;

    public OidcUserServiceImpl(UserService userService) {
        this.userService = userService;
        this.delegate = new OidcUserService();
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser user = delegate.loadUser(userRequest);
        final Optional<User> principalLogin = userService.findByLogin(user.getPreferredUsername());
        if (!principalLogin.isPresent()) {
            throw new UnexpectedException("Oidc user not found in db");
        }
        List<GrantedAuthority> rolesAsAuthorities = getRolesAsAuthorities(user);
        return new CustomOidcUserDetailsImpl(user, rolesAsAuthorities, principalLogin.get().getId());
    }

    private List<GrantedAuthority> getRolesAsAuthorities(OidcUser user) {
        List<GrantedAuthority> additionalAuthorities = new ArrayList<>();
        Collection<? extends GrantedAuthority> groups = user.getAuthorities();
        if (groups != null) {
            for (GrantedAuthority group : groups) {
                if (group.getAuthority().startsWith(REQUIRED_ROLE_NAME_BEGIN))
                    additionalAuthorities.add(new SimpleGrantedAuthority(group.getAuthority()));
            }
        }
        return additionalAuthorities;
    }
}
