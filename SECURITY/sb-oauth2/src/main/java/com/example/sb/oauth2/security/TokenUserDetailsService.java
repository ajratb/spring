package com.example.sb.oauth2.security;

import org.springframework.security.oauth2.jwt.Jwt;

public interface TokenUserDetailsService {
    UserDetailsImpl loadUserByToken(Jwt jwt);
}
