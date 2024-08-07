package com.example.sb.oauth2.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

@Getter
public class AuthenticationTokenImpl extends AbstractAuthenticationToken {

    private final Jwt credentials;
    private final UserDetailsImpl principal;

    public AuthenticationTokenImpl(Jwt credentials, UserDetailsImpl principal,
                                   Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.setAuthenticated(true);
        this.credentials = credentials;
        this.principal = principal;
    }

}
