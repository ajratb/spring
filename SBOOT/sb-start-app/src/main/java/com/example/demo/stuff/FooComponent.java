package com.example.demo.stuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayrat
 */
@Component("fooCompo")
public class FooComponent implements MyComponent{

	@Autowired
	@Qualifier("second")
	MyBean been;

	@Override
	public String sayWhoIAm() {
		StringBuilder sb = new StringBuilder("\nFOOOOOooooo-ZZZZZzzZZz. ").append("With bean: ")
				.append(been.getBeanName()).append("\n");
        return sb.toString();
    }

}
