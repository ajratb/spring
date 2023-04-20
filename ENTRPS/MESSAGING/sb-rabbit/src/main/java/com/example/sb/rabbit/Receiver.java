package com.example.sb.rabbit;

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
     * For string messages receiving.
     */
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        // if comment countDown() out then await timer will work
        latch.countDown();
    }

    /**
     * For spring.messaging.Message messages receiving.
     */
    public void receiveSpringMessage(Message<String> msg) {
        System.out.println("Received <" + msg + ">");
        // if comment countDown() out then await timer will work
        latch.countDown();
    }

    /**
     * This method suits for ..amqp.core.Message messages and messages are sent by management tools.
     */
    public void receiveBytes(byte[] content){
        System.out.println("Received <" + new String(content, Charset.defaultCharset()) + ">");
        // if comment countDown() out then await timer will work
        latch.countDown();
    }

    /**
     * It won't be working! Use receiveBytes method for ..amqp.core.Message receiving.
     */
    public void receiveRabbitMessage(org.springframework.amqp.core.Message msg) {

        // for Object it will not be working too.
        //org.springframework.amqp.core.Message msg = (org.springframework.amqp.core.Message)obj;

        System.out.println("Received <" + new String(msg.getBody(), StandardCharsets.UTF_8) + ">");
        // if comment countDown() out then await timer will work
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
