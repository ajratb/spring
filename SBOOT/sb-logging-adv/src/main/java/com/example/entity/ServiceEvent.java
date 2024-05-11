package com.example.entity;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ServiceEvent extends ApplicationEvent {

    @Getter
    ServiceMessage message;
    public ServiceEvent(ServiceMessage message) {
        super(message);
        this.message = message;
    }

}
