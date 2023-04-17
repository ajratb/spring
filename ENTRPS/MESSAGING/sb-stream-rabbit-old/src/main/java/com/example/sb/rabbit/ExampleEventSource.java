package com.example.sb.rabbit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ExampleEventSource {
    @Output("exampleChannel")
    MessageChannel example();
}
