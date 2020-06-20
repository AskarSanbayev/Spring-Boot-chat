package com.chat.cyber.security.jwt;

import com.chat.cyber.model.Role;
import com.chat.cyber.util.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    private static final String INVALID_JWT_TOKEN_MESSAGE = "JWT token is expired or invalid";

    @Value("${auth.secret}")
    private String secret;

    @Value("${auth.accessjwtExpiration}")
    private int accessJwtExpirationInMs;

    @Value("${auth.refreshJwtExpiration}")
    private int refreshJwtExpirationInMs;

    private JwtUserDetailService jwtUserDetailService;

    public JwtUserDetailService getJwtUserDetailService() {
        return jwtUserDetailService;
    }

    @Autowired
    public void setJwtUserDetailService(JwtUserDetailService jwtUserDetailService) {
        this.jwtUserDetailService = jwtUserDetailService;
    }

    public String generateAccessToken(String login, Set<Role> roles) {
        Claims claims = extractClaim(login, roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessJwtExpirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)//
                .compact();
    }

    public String generateRefreshToken(String username, Set<Role> roles) {
        Claims claims = extractClaim(username, roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshJwtExpirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)//
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String token = req.getHeader(AppConstants.AUTHORIZATION_HEADER);
        return token;
    }

    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    private Set<Role> getRoleNames(Set<Role> userRoles) {
        Set<Role> result = new HashSet<>(userRoles);
        return result;
    }

    private Claims extractClaim(String login, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("roles", getRoleNames(roles));
        return claims;
    }
}
