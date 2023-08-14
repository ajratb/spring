package com.example.sblqbsclickhouse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SbLqbsClickhouseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SbLqbsClickhouseApplication.class, args);
	}

	public static int counter;
	@Override
	public void run(String...args) throws Exception {
		log.info("Increment counter");
		counter++;
		log.info("counter is: {}", counter);
	}

}
