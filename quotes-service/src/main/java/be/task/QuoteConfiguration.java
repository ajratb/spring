package be.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuoteConfiguration {

    @Bean
    ExecutorService worker() {
        return Executors.newSingleThreadExecutor();
    }
}
