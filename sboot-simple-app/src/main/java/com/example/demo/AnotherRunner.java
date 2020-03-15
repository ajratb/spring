package com.example.demo;

import com.example.demo.stuff.IComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayrat
 */
@Component
public class AnotherRunner implements CommandLineRunner{

    @Autowired
    @Qualifier
            ("fooCompo")
//        ("barCompo")
    IComponent compo; 
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println(compo.sayWhoIAm());
        
    }

}
