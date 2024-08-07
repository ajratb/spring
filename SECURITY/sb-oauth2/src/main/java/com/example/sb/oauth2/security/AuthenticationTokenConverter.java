package com.example.sb.oauth2.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthenticationTokenConverter extends Converter<Jwt, AbstractAuthenticationToken> {
}
