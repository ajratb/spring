package com.example.service;

import com.example.entity.Person;
import com.example.entity.ServiceEvent;
import com.example.entity.ServiceMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class PersonService {

    final ApplicationEventPublisher publisher;
    final ObjectMapper jsonMapper;

    public void createNewPosition() throws IOException {

        var person = new Person();
        log.debug("Publish event of person creation. Person id is '{}'", person.getId());
        String bodyJson = jsonMapper.writeValueAsString(person);
        ServiceEvent serviceEvent = new ServiceEvent(new ServiceMessage(UUID.randomUUID(), person.getId(), "Person", bodyJson));
        publisher.publishEvent(serviceEvent);
        log.debug("Finish to publish event for person creation. Person id is: {}", person.getId());
    }


}
