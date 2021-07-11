package spring.examples.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;

@Component // to put instance in context. You also can use @Service, @Repository
//@PropertySource({"classpath:persistence-{$envTarget:mysql}.properties"}) - it's not working
@PropertySource("classpath:app.properties")//for using @Value
public class AppWithProps {

	@Autowired
	Message msg;

	private final String value;

	public AppWithProps(@Value("${my_value}") String value) {//Use @Value with @PropertySource
		super();
		this.value = value;
	}

	public static void main(String[] args) {

		// ApplicationContext
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppWithPropsConf.class)) {

			AppWithProps app = ctx.getBean(AppWithProps.class);
			app.msg.printMsg();

			Message msgBean = ctx.getBean(Message.class);
			msgBean.printMsg();

			System.out.println("Value is: " + app.value);
		}
	}
}
