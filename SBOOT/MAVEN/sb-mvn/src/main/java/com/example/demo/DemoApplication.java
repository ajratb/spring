package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Value("${app.name}")
	private String applicationName;

	@Value("${app.version}")
	private String buildVersion;

	@Value("${build.timestamp}")
	private String buildTimestamp;

	@Value("${app.artifact}")
	private String artifactId;

	private static Logger LOG = LoggerFactory
			.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("app name: {}", applicationName);
		LOG.info("app version: {}", buildVersion);
		LOG.info("app timestamp: {}", buildTimestamp);
		LOG.info("app artifact: {}", artifactId);
	}
}
