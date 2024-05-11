package com.example.outbox;

import com.example.entity.InboxMessageEvent;
import com.example.entity.Person;
import com.example.entity.ServiceEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OutboxMessageProducer {

    final ApplicationEventPublisher publisher;
    final ObjectMapper objectMapper;

    @EventListener
    public void listenEvent(ServiceEvent event) throws IOException {

        log.info("Start event publishing. Message id is: {}", event.getMessage().getId());
        Person person = objectMapper.readValue(event.getMessage().getBodyJson(), Person.class);

        publisher.publishEvent(new InboxMessageEvent(event.getMessage()));
        log.info("Finish event publishing. Person id is: {}", event.getMessage().getId());
    }
}
