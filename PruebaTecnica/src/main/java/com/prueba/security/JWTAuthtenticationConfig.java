package com.prueba.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.prueba.shared.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JWTAuthtenticationConfig {

	public String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        
        SecretKey key = Keys.hmacShaKeyFor(Constants.SUPER_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        
        String token = Jwts
                .builder()
                .setId("testJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
                .signWith(key)
                .compact();

        return "Bearer " + token; 
    }
}