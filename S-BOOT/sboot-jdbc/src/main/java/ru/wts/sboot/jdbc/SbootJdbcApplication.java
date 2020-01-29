package ru.wts.sboot.jdbc;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement
//@EnableAutoConfiguration
//@ComponentScan({"ru.wts.sboot.jdbc"})
//@EnableJpaRepositories("Repository package")
@SpringBootApplication
public class SbootJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootJdbcApplication.class, args);
	}

}
