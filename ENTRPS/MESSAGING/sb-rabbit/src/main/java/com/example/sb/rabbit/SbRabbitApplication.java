package com.example.sb.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(RabbitConfig.class)
public class SbRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbRabbitApplication.class, args).close();
	}
}
