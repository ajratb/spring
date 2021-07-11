package spring.examples.base;

//import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import spring.examples.base.beans.MyService;
import spring.examples.base.beans.Student;

@Configuration
@ComponentScan("spring.examples.base")
@Import({spring.examples.props.AppWithPropsConf.class, AnotherConfig.class})//AnotherConfig.class added just for example!
public class AppConfig {

	@Bean
	public MyService myService() {
		MyService srv = new MyService();
		return srv;
	}

	@Bean
	@Primary
//    @Qualifier("first")
	public Student firstStudent() {
		return new Student("Petya", 16);
	}

	@Bean
	@Qualifier("second")
//    @Resource(name="second")
	public Student secondStudent() {
		return new Student("Vasya", 24);
	}

}
