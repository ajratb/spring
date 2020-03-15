package com.example.demo.stuff;

import org.springframework.stereotype.Component;

/**
 *
 * @author ayrat
 */
@Component("barCompo")
public class BarComponent implements IComponent{

    @Override
    public String sayWhoIAm() {
        return "\nBAR-BAR-BAR-zzzzzzzzzz...\n";
    }
}
