package com.example.sb.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@EnableBinding(Sink.class)
@Service
@Slf4j
public class ExampleEventHandler {

    @StreamListener(target = Sink.INPUT)
    public void receiveEvent(ExampleEvent simplestLoad) {
        //Process the Event
        log.info("Message received: {}", simplestLoad.data());
    }
}
