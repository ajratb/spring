package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.demo.stuff.BarBean;
import com.example.demo.stuff.FooBean;
import com.example.demo.stuff.MyBean;

/**
 *
 * @author ayrat
 */
@Configuration
public class DemoConfiguration {
	@Bean
	@Primary
//    @Qualifier("first")
	public MyBean barBean() {
		return new BarBean();
	}

	@Bean
	@Qualifier("second")
//    @Resource(name="second")
	public MyBean fooBean() {
		return new FooBean("fOOooZzzzzzz");
	}
}
