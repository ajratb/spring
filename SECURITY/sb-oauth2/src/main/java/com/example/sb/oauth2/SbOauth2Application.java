package com.example.sb.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SbOauth2Application implements CommandLineRunner {

	@Value("${spring.security.oauth2.resourceserver.jwt.issuerUri}") String testProp;
	@Value("${spring.security.oauth2.resourceserver.jwt.jwkSetUri}") String testProp2;

	@Override
	public void run(String... args) throws Exception {
		log.info(testProp);
		log.info(testProp2);
	}

	public static void main(String[] args) {
		SpringApplication.run(SbOauth2Application.class, args);
	}

}
