package ru.wts.sboot.data.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

//    @Autowired
//       @Qualifier("j") //DOESN'T WORK with @Autowired! (NoSuchBeanDefinitionException..)
    
    //ALL THIS WORKS with or without it and in any order and combinations!
//    @Qualifier("ja")
//    @Autowired
//    TransactionsAppRunner 
//    ... 
//    CommandLineRunner 
//                appRunner;
    
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

}
