package com.example.sb.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("CommentedOutCode")
@SpringBootApplication
//@EnableConfigurationProperties(RabbitConfig.class)
public class SbRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbRabbitApplication.class, args).close();
	}

//	@Bean
//	public ApplicationRunner runner(RabbitTemplate rabbitTemplate){
//		return args -> rabbitTemplate.convertAndSend("");
//	}
}
