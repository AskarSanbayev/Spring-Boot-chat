package com.chat.cyber.config;

import com.chat.cyber.service.UserService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        KeycloakPrincipal userPrincipal = (KeycloakPrincipal) authentication.getPrincipal();
        IDToken idToken = userPrincipal.getKeycloakSecurityContext().getIdToken();
        userService.create(idToken);
    }
}
