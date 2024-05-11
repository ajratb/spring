package com.example;

import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@RequiredArgsConstructor
@SpringBootApplication
public class LoggingExampleApplication implements CommandLineRunner {

    final PersonService personService;

    @Override
    public void run(String... args) throws IOException {
        personService.createNewPosition();

    }

    public static void main(String[] args) {
        SpringApplication.run(LoggingExampleApplication.class, args);
    }

}
