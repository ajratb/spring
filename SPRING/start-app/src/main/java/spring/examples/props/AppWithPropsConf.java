package spring.examples.props;

import org.springframework.beans.factory.annotation.Autowired;
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
@ComponentScan("wts.props")
@PropertySource("classpath:app.properties")
public class AppWithPropsConf {

	@Autowired
	Environment env;

	//
	@Bean
	Message message() {
		return new Message(env.getProperty("msg"));
	}

}
