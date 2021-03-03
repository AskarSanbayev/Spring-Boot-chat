package com.chat.cyber.config;

import com.chat.cyber.service.impl.OidcUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@Slf4j
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final OidcUserServiceImpl oidcUserService;
    @Value("${my.authorization-url}")
    private String authorizationUrl;
    @Value("${my.logout-url}")
    private String logoutUrl;

    public OAuth2WebSecurityConfig(OidcUserServiceImpl oidcUserService) {
        this.oidcUserService = oidcUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/login", "/api/oauth2/authorization/**",
                        "/api/user/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .successHandler(new OAuth2AuthenticationSuccessHandler())
                .failureHandler(new CustomAuthenticationFailureHandler())
                .userInfoEndpoint(c -> c.oidcUserService(oidcUserService))
                .loginPage("/login")
                .authorizationEndpoint(c -> c.baseUri(authorizationUrl))
                .and().logout(c -> c.logoutUrl(logoutUrl))
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and().csrf().disable();
    }
}

