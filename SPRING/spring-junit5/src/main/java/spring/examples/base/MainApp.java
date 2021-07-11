package spring.examples.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import spring.examples.props_usage.Message;

@Component//or Service or Repository
public class MainApp {

	@Autowired
	@Qualifier("second")
//    @Resource(name="second")
	private Student second;
	
	@Autowired
	Message msg;

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {

			MainApp app = (MainApp) ctx.getBean("mainApp");// mainApp - follow convention!


			System.out.println("first student name is: " + app.second.getName());

			// AppWithPropsConf is also available
			Message msgBean = ctx.getBean(Message.class);
			msgBean.printMsg();
		}
	}
}
