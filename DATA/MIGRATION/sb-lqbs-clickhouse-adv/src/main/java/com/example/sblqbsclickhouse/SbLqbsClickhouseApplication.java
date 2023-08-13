package com.example.sblqbsclickhouse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class SbLqbsClickhouseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SbLqbsClickhouseApplication.class, args);
	}

	@Autowired ClickHouseRepository clickHouseRep;

	public static int counter;
	@Override
	public void run(String...args) throws Exception {
		log.info("Increment counter");
		counter++;
		log.info("counter is: {}", counter);
		long count = clickHouseRep.count();
		log.info("count is: {}", count);
		Optional<ClickHouseEntity> found = clickHouseRep.findById(1L);
		found.ifPresent(entity -> log.info("TITLE IS: {}", entity.getTitle()));
	}

}
