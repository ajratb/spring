package com.example.sb.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DebugController {

    @GetMapping("/test")
    String test(){
        log.info("TEST METHOD DEBUG CONTROLLER");
        return "TEST";
    }
}
