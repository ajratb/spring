package com.example.inbox;

import com.example.entity.InboxMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InboxMessageProcessor {

    @EventListener
    public void processMessage(InboxMessageEvent serviceMessageEvent){
        MDC.put("MESSAGE_ID", serviceMessageEvent.getMessage().getId().toString());
        log.info("Start to process inbox message. Message id is: {}", serviceMessageEvent.getMessage().getId());
        MDC.clear();
    }
}
