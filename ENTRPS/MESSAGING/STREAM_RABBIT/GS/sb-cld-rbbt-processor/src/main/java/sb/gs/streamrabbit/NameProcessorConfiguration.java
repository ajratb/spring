package sb.gs.streamrabbit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.function.Function;

@Configuration
public class NameProcessorConfiguration {

	// tag::processname[]
	@Bean
	public Function<String, Person> processName() {
		return name -> new Person(name, new Date().getTime());
	}
	// end::processname[]
}
