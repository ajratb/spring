package com.example.sb.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
@Component
@Slf4j
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    @Value("${rabbitmq.topic}") String topic;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(topic, "foo.bar.baz", "Hello from RabbitMQ!");
        boolean awaitResult = receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        log.info("Runner finished successful: {}", awaitResult);
    }
}
