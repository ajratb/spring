package spring.examples.base.beans;

public class MyService {

	public void printMsg() {
		System.out.println("myService is working");
	}

	public void printMsg(String val) {
		System.out.println("myService is working [" + val + "]");
	}
	
	public String getMsg() {
		return "hello";
	}
}
