package sboot.gradle.application;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sboot.gradle.service.MyService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * not the same package with service, 
 * so common root package is pointed here
 */
@SpringBootApplication(scanBasePackages = "sboot.gradle")
@RestController
public class DemoApplication {

	private final MyService myService;

	public DemoApplication(MyService myService){
		this.myService = myService;
	}

	@GetMapping("/")
	public String home(){
		return myService.message();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
