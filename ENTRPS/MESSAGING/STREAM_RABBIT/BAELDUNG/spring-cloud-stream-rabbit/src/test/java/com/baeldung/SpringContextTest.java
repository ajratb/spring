package com.baeldung;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.baeldung.spring.cloud.stream.rabbit.MultipleOutputsServiceApplication;

@SpringBootTest(classes = MultipleOutputsServiceApplication.class)
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
