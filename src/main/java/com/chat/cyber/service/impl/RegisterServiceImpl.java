package com.chat.cyber.service.impl;

import com.chat.cyber.dto.request.RegUserDataDto;
import com.chat.cyber.exception.RestException;
import com.chat.cyber.exception.UnexpectedException;
import com.chat.cyber.model.User;
import com.chat.cyber.service.RegistrationService;
import com.chat.cyber.service.UserService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

import static com.chat.cyber.util.AppConstants.DEFAULT_USER_ROLE;

@Service
public class RegisterServiceImpl implements RegistrationService {

    @Value("${keycloak.realm-name}")
    private String realmName;
    private static final int CREATED_STATUS = 201;

    private final Keycloak keycloak;
    private final UserService userService;

    public RegisterServiceImpl(Keycloak keycloak, UserService userService) {
        this.keycloak = keycloak;
        this.userService = userService;
    }

    @Override
    public void registerUser(RegUserDataDto regUserDataDto, Locale locale) {
        Optional<User> loginUser = userService.findByLogin(regUserDataDto.getLogin());
        Optional<User> emailUser = userService.findByEmail(regUserDataDto.getEmail());
        if (loginUser.isPresent()) {
            throw new RestException("Login already exists");
        }
        if (emailUser.isPresent()) {
            throw new RestException("Email already exists");
        }
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(regUserDataDto.getPassword());
        credential.setTemporary(false);

        UserRepresentation userReq = new UserRepresentation();
        userReq.setUsername(regUserDataDto.getLogin());
        userReq.setEmail(regUserDataDto.getEmail());
        userReq.setCredentials(Collections.singletonList(credential));
        userReq.setEnabled(true);
        userReq.setRealmRoles(Collections.singletonList(DEFAULT_USER_ROLE));

        Response result = keycloak.realm(realmName).users().create(userReq);
        if (result.getStatus() != CREATED_STATUS) {
            throw new UnexpectedException("Error registering user");
        }
        userService.create(regUserDataDto);
    }
}
