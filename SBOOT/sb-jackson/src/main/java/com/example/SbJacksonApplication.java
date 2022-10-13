package com.example;

import com.example.Person.Gender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.Person.Gender.*;

@Slf4j
@SpringBootApplication
public class SbJacksonApplication implements CommandLineRunner {

	@Autowired
	ObjectMapper jsonMapper;

	public static void main(String[] args) {
		SpringApplication.run(SbJacksonApplication.class, args);
	}

	@Override
	public void run(String... args) throws JsonProcessingException {
		log.info("Hello World !");
		Person feodor = new Person("Feodor", "Mihailovich", MALE, 38);
		String toJson = jsonMapper.writeValueAsString(feodor);
		log.info(toJson);
		String fromJson = "{\"firstName\":\"Svetlana\",\"lastName\":\"Egorovna\",\"gender\":\"Female\",\"age\":24}";
		Person person = jsonMapper.readValue(fromJson, Person.class);
		log.info(person.toString());
	}
}
