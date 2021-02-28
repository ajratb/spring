package com.example.demo;

import java.util.Arrays;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication // (exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication is same as @Configuration @EnableAutoConfiguration @ComponentScan
public class DemoApplication {

	// No need for it - it'll be run anyway
//    @Autowired
//    AnotherRunner runner;

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	Environment env;

	public static void main(String[] args) {
		System.out.println("START MAIN METHOD");
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:/n");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

			String[] actProfs = env.getActiveProfiles();
			String[] defProfs = env.getDefaultProfiles();

			if (actProfs.length > 0)
				log.info("Active Profiles: {}", actProfs[0]);// prod or dev

			log.info("Default Profiles: {}", defProfs[0]);// default

		};
	}

	@PreDestroy
	public void onExit() {
		log.info("###STOPing###");
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			log.error("", e);
		}
		log.info("###STOP FROM THE LIFECYCLE###");
	}
}
