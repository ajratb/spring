/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess715.study;

/**
 * Пример с оф.странички spring.boot.
 * http://projects.spring.io/spring-boot/
 * и здесь:
 * http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started-introducing-spring-boot
 * (11. Developing your first Spring Boot application)
 * @author ayrat
 */
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
//import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@SpringBootApplication//!
public class Example {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Ты в первом приложении, работающем на порту 8081. \nДобавь "
                + "\"/next\" к запросу, "
                + "чтобы увидеть следующее сообщение";
    }
    
    @RequestMapping("/next")    
    String next() {
        return "Все работает! Второе приложение на порту 8082";
    }

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Example.class, args);
//    }

}
