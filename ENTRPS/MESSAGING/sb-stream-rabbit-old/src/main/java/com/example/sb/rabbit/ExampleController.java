package com.example.sb.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * It doesn't change anything! The same exception is thrown.
 */
@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExamplePublisherService publisherService;

    @RequestMapping("/hello")
    public String hello(){
        publisherService.handleCargoBookedEvent(new ExampleEvent("some msg"));
        return "success";
    }
}
