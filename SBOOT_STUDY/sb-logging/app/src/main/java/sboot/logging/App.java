package sboot.logging;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class App {
	
	private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
    }
    
    @Autowired
    Environment env;
    
    @Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:\n");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
			log.trace("TRACE");
			log.debug("DEBUG");
			log.info("INFO");
			log.warn("WARN");
			log.error("ERROR");
			
			String[] actProfs = env.getActiveProfiles();
			String[] defProfs = env.getDefaultProfiles();
			
			log.info("Active Profiles: {}", actProfs[0]);//prod or dev
			log.info("Default Profiles: {}", defProfs[0]);//default
			
			throw new RuntimeException("TEST RUNTIME EXCEPTION");
		};
	}
}
