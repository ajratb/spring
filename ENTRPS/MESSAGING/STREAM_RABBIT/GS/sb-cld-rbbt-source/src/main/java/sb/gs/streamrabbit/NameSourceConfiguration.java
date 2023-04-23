package sb.gs.streamrabbit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class NameSourceConfiguration {

	// tag::supplyname[]
	@Bean
	public Supplier<String> supplyName() {
		return () -> "Christopher Pike";
	}
	// end::supplyname[]
	@Bean
	public Supplier<String> supplyAnotherName() {
		return () -> "Serg Gorely";
	}
}
