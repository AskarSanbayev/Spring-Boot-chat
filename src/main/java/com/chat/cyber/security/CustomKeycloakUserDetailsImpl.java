package com.chat.cyber.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@ToString
public class CustomKeycloakUserDetailsImpl implements CustomUserDetails {

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String uuid;

    @Getter
    @Setter
    private boolean accountNonExpired = true;

    @Getter
    @Setter
    private boolean accountNonLocked = true;

    @Getter
    @Setter
    private boolean credentialsNonExpired = true;

    @Getter
    @Setter
    private boolean enabled = true;

    @Getter
    Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return null;
    }

    public CustomKeycloakUserDetailsImpl(KeycloakAuthenticationToken principal) {
        IDToken idToken = principal.getAccount().getKeycloakSecurityContext().getIdToken();
        this.username = idToken.getPreferredUsername();
        this.email = idToken.getEmail();
        this.uuid = "test";
        this.accountNonExpired = idToken.isExpired();
        this.authorities = principal.getAuthorities();
        this.enabled = idToken.isActive();
    }
}

