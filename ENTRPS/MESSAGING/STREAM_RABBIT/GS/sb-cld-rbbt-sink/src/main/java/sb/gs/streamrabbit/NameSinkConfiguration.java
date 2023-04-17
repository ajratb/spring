package sb.gs.streamrabbit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class NameSinkConfiguration {

	// tag::namesink[]
	@Bean
	public Consumer<Person> nameSink() {
		return person -> {
			System.out.println(person.name());
			System.out.println(person.processedTimestamp());
		};
	}
	// end::namesink[]
}
