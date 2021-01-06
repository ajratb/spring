package ru.waytosky.spring;

import java.io.File;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {

		try (ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml")) {
			System.out.println("Before getting App from context");
			App app = (App) ctx.getBean("app");

			System.out.format("%s\n", app.client.toString());
			System.out.format("%s\n", app.eventLogger.toString());

			//initate fields with new value
//			app.client = new Client("1", "John Smith");
			EventLogger logger = (ConsoleEventLogger)ctx.getBean("eventLogger");//new ConsoleEventLogger();
			System.out.format("Loggers are equal: %b\n", logger.equals(app.eventLogger));
			app.eventLogger = logger;
			
			//
			//every time new instance of event is created!
			app.logEvent((Event) ctx.getBean("event"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			app.logEvent((Event) ctx.getBean("event"));
//                "Some event for user 1");
		}
//        app.client=new Client("1","John Smith");
//        app.eventLogger=new ConsoleEventLogger();
		File file = new File("./src/main/resources/log.txt");
		System.out.format("file exists:%b - can write:%b - %s\n", file.exists(), file.canWrite(),
				file.getAbsolutePath());

	}
}
