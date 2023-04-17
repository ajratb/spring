package com.example.sb.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@EnableBinding(ExampleEventSource.class)
@Service
@RequiredArgsConstructor
@Slf4j
public class ExamplePublisherService {

    private final ExampleEventSource exampleEventSource;

    public void handleCargoBookedEvent(ExampleEvent simplest){
        exampleEventSource.example().send(MessageBuilder.withPayload(simplest).build());
    }
}
