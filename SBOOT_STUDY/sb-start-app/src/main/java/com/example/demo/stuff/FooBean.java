package com.example.demo.stuff;

/**
 *
 * @author ayrat
 */
public class FooBean implements MyBean {

	final String name;

	public FooBean(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getBeanName() {
		StringBuilder sb = new StringBuilder("It's FooBean with name: ").append(name).append("!");
		return sb.toString();
	}

}
