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
 * @author ayrat
 */
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
//import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Как жэ это работает?!";
    }
    
    @RequestMapping("/next")    
    String next() {
        return "это работает! (только не забудь /next)";
    }

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Example.class, args);
//    }

}
