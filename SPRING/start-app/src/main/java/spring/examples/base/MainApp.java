package spring.examples.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import spring.examples.base.beans.MyService;
import spring.examples.base.beans.Student;
import spring.examples.props.Message;

@Component//or Service or Repository
public class MainApp {

	@Autowired
	private MyService myService;

	@Autowired
	@Qualifier("second")
//    @Resource(name="second")
	private Student second;

//    @Autowired
//    void setMyService(MyService myService){
//        this.myService= myService;
//    }
//    @Autowired
//    MainApp(MyService myService) {
//        this.myService = myService;
//    }
	@Autowired
	Message msg;

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {

			// if you don't use ComponentScan("pkg_name") with AppConfig
			// then use this code:

			// AnnotationConfigApplicationContext ctx = new
			// AnnotationConfigApplicationContext();
			// ctx.register(AppConfig.class);
			// ctx.scan("wts");
			// ctx.refresh();

			MainApp app = (MainApp) ctx.getBean("mainApp");// mainApp - follow convention!
			// Service as a part mainApp component
			app.myService.printMsg();

			// Service retrieved from configuration
			MyService srvBean = (MyService) ctx.getBean(MyService.class);
			srvBean.printMsg("from srv");

			System.out.println("first student name is: " + app.second.getName());

			// AppWithPropsConf is also available
			Message msgBean = ctx.getBean(Message.class);
			msgBean.printMsg();
			
			//from gradle initiated project
			System.out.println(new MainApp().getGreeting());
		}
	}

	//from gradle initiated project
	public String getGreeting() {
		return "Hello World!";
	}
}
