package com.example.sb.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * Tried to use instead of Sink.
 * Compare with Sink!
 */
public interface ExampleSink {
    String INPUT = "exampleChannel";

    /**
     * @return input channel.
     */
    @Input(ExampleSink.INPUT)
    MessageChannel example();
}
