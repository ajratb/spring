package com.example.entity;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class InboxMessageEvent extends ApplicationEvent {

    @Getter
    final UUID id;
    @Getter
    ServiceMessage message;

    public InboxMessageEvent(ServiceMessage message) {
        super(message);
        this.id = UUID.randomUUID();
        this.message = message;
    }
}
