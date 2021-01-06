package wts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("wts")
public class AppConfig {

	@Bean
	public MyService myService() {
		MyService srv = new MyService();
		return srv;
	}

}
