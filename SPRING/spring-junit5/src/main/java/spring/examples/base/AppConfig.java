package spring.examples.base;

//import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("spring.examples.base")
@Import({spring.examples.props_usage.AppWithPropsConf.class, AnotherConfig.class})//AnotherConfig.class added just for example!
public class AppConfig {

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
