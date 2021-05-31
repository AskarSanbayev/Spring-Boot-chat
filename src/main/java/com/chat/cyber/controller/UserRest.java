package com.chat.cyber.controller;

import com.chat.cyber.security.CustomUserDetails;
import com.chat.cyber.service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("/api/rest/security")
public class UserRest {

    private final ProfileService profileService;

    public UserRest(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/principal")
    public @ResponseBody
    CustomUserDetails principal(@ApiIgnore Principal principal) {
        return profileService.getUserDetails(principal);
    }

    @GetMapping("/roles")
    public @ResponseBody
    String getRoles(@ApiIgnore Principal principal) {
        return profileService.getUserRoles(principal).toString();
    }

}
