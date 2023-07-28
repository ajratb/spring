package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.stuff.MyBean;
import com.example.demo.stuff.MyComponent;

/**
 *
 * @author ayrat
 */
@Component
public class AnotherRunner implements CommandLineRunner {

	@Autowired
	@Qualifier
	("fooCompo")
//        ("barCompo")
	MyComponent compo;

	@Autowired
//	@Qualifier("second")
	MyBean bean;

	@Value("${some}") String someValue;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("START ANOTHER_RUNNER");
		System.out.println(compo.sayWhoIAm());
		System.out.println("My bean is: " + bean.getBeanName() + "\n");
		System.out.println(someValue);
	}

}
