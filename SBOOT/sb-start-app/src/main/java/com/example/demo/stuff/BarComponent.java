package com.example.demo.stuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayrat
 */
@Component("barCompo")
public class BarComponent implements MyComponent {

	@Autowired
	MyBean been;

	@Override
	public String sayWhoIAm() {
		StringBuilder sb = new StringBuilder("\nBAR-BAR-BAR-zzzzzzzzzz. ").append("With bean: ")
				.append(been.getBeanName()).append("\n");
		return sb.toString();
	}
}
