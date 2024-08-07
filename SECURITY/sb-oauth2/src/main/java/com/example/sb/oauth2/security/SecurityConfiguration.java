package com.example.sb.oauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.method.P;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Profile("no-auth")
    @Bean
    public SecurityFilterChain noAuthFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorize -> authorize.anyRequest().permitAll());
        return http.build();
    }

    @Profile("!no-auth")
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http, AuthenticationTokenConverter converter) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers("/messages/**").access(hasScope("message:read"))
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(converter)
                        )
                );
        return http.build();
    }

    @Bean
    public AuthenticationTokenConverter commonAuthenticationTokenConverter(
            TokenUserDetailsService tokenUserDetailsService) {
        return jwt -> {
            UserDetailsImpl details = tokenUserDetailsService.loadUserByToken(jwt);
            return new AuthenticationTokenImpl(jwt, details, details.getAuthorities());
        };
    }

    @Bean
    public TokenUserDetailsService commonTokenUserDetailsService( ) {
        return new TokenUserDetailsServiceImpl();
    }
}

