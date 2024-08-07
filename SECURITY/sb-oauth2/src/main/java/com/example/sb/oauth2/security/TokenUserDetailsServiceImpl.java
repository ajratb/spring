package com.example.sb.oauth2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;

public class TokenUserDetailsServiceImpl implements TokenUserDetailsService {

    @Override
    public UserDetailsImpl loadUserByToken(Jwt jwt) {
        UserDetailsImpl details = new UserDetailsImpl();
        //fillUserFromToken(details, jwt);
        return details;

    }
}
