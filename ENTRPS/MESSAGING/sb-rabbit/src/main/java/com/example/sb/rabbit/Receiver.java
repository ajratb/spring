package com.example.sb.rabbit;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@SuppressWarnings("unused")
@Component
public class Receiver {
    private final CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        // if comment countDown() out then await timer will work
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
