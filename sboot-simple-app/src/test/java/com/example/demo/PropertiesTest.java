package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author ayrat
 */
@SpringBootTest
@TestPropertySource(properties = {"some.prop=7899"})
public class PropertiesTest {
    
    @Value("${some.prop}")
    int fromProps;
    
    @Test
    void testPropSource(){
        System.out.println("some prop: " + fromProps);
    }

}
