package spring.examples.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author BikchentaevAA
 */
@Configuration
@ComponentScan("spring.examples.props")
@PropertySource("some.properties")//classpath:some.properties")
public class AppWithPropsConf {

//	@Autowired
//	Environment env;
	
	@Value("${msg}") String msg;

	//
	@Bean
	Message message() {
		return new Message(msg);
//		return new Message(env.getProperty("msg"));
	}

}
