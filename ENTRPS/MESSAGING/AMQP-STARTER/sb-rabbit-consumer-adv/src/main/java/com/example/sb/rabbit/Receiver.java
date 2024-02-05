package com.example.sb.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * Receive method used by MessageListenerAdapter. See RabbitConfig.
 */
@SuppressWarnings("unused")
@Component
public class Receiver  {
    private final CountDownLatch latch = new CountDownLatch(1);

    /**
     * For spring.messaging.Message messages receiving.
     */
    @RabbitListener(queues = "sb-queue2")
    public void receiveSpringMessage(Message<String> msg) {
        System.out.println("Received <" + msg + ">");
        // if comment countDown() out then await timer will work
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
