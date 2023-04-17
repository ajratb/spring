package com.example.sb.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
@Slf4j
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {


    private final ExamplePublisherService publisherService;

    private final ExampleEventHandler eventHandler;

    @Override
    public void run(String... args) {
        System.out.println("Sending message...");
        eventHandler.receiveEvent(new ExampleEvent("dfs"));
        publisherService.handleCargoBookedEvent(new ExampleEvent("Hello from RabbitMQ-Stream!"));
        log.info("Runner finished");
    }
}
