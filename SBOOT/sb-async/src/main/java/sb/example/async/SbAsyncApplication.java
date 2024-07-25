package sb.example.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@Slf4j
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SbAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbAsyncApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}

	@Autowired GitHubLookupService service;


	//@Scheduled(fixedDelay = 1000)
	public void runSome(){
		try {
			service.findUser("some");
		}catch (InterruptedException interruptedEx){
			log.error("InterruptedException");
		}
	}

	@Autowired IntegerService integerService;
	@Scheduled(fixedDelay = 1000)
	public void runIntegerService() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> integer = integerService.getInteger();
		List<CompletableFuture<Integer>> tasks = new ArrayList<>();
		tasks.add(integer);
		CompletableFuture.allOf(integer).join();
		log.info("integer is {}", integer.get());
	}

}
