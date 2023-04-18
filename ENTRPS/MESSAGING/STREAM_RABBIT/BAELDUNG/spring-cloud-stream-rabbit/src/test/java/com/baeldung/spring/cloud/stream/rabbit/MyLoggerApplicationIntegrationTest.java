package com.baeldung.spring.cloud.stream.rabbit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import com.baeldung.spring.cloud.stream.rabbit.model.LogMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = MyLoggerServiceApplication.class)
@DirtiesContext
//@EnableBinding(Processor.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyLoggerApplicationIntegrationTest {

    @Autowired
    private Processor pipe;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void whenSendMessage_thenResponseShouldUpdateText() {
        pipe.input()
            .send(MessageBuilder.withPayload(new LogMessage("This is my message"))
                .build());

        Object payload = messageCollector.forChannel(pipe.output())
            .poll()
            .getPayload();

        assertEquals("{\"message\":\"[1]: This is my message\"}", payload.toString());
    }
}
