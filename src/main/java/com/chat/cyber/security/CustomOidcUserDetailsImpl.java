package com.chat.cyber.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@ToString
public class CustomOidcUserDetailsImpl implements OidcUser, CustomUserDetails {

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String uuid;

    @Getter
    @Setter
    private boolean enabled = true;

    @Getter
    Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private OidcUser oidcUser;

    public CustomOidcUserDetailsImpl(OidcUser oidcUser, List<? extends GrantedAuthority> rolesAsAuthorities,
                                     Long userId) {
        this.oidcUser = oidcUser;
        this.username = oidcUser.getPreferredUsername();
        this.email = oidcUser.getEmail();
        this.uuid = oidcUser.getSubject();
        this.authorities = rolesAsAuthorities;
        this.id = userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public Map<String, Object> getClaims() {
        return oidcUser.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        return oidcUser.getIdToken();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oidcUser.getAttributes();
    }

    @Override
    public String getName() {
        return oidcUser.getPreferredUsername();
    }
}

