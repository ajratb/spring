package com.example.sbwithssl;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/**")
//                .hasRole("USER").and().formLogin();
                .permitAll();
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("123")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

//    @Bean
//    RestTemplate restTemplate() throws Exception {
//        SSLContext sslContext = new SSLContextBuilder()
//                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
//                .build();
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
//        HttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(socketFactory)
//                .build();
//        var factory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//        return new RestTemplate(factory);
//    }


}
