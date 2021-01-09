package com.example.demo.stuff;

import org.springframework.stereotype.Component;

/**
 *
 * @author ayrat
 */
@Component("fooCompo")
public class FooComponent implements IComponent{

    @Override
    public String sayWhoIAm() {
        return "\nFOOOOOooooo-ZZZZZZZzzzzzzzzzzZZZzzzzzz...........\n";
    }

}
