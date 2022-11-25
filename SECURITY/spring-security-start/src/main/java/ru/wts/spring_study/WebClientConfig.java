package ru.wts.spring_study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebClientConfig extends WebSecurityConfigurerAdapter {
//    @Primary
//    @Bean
//    WebClient webClient() {//OAuth2AuthorizedClientManager authorizedClientManager) {
//        return WebClient.builder()
//                .baseUrl("https://ya.ru")
//                .defaultHeaders(httpHeaders -> {
//                    httpHeaders.setBasicAuth("user", "123456", StandardCharsets.ISO_8859_1);
//                    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//                })
//
////                .apply(oauth2.oauth2Configuration())
//                .build();
//    }
}
