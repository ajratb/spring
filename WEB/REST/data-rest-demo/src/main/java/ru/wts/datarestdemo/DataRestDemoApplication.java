package ru.wts.datarestdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DataRestDemoApplication {

	@Autowired
	PersonRepository persons;

	@PostConstruct
	public void init() {
		Person person = new Person();
		person.setId(1L);
		person.setFirstName("John");
		persons.save(person);
	}

	public static void main(String[] args) {
		SpringApplication.run(DataRestDemoApplication.class, args);
	}

}
