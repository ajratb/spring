package sboot.gradle.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sboot.gradle.service.MyService;

@SpringBootTest
class DemoApplicationTest {


	@Autowired
	private MyService myService;

	@Test
	void contextLoads() {
		assertThat(myService.message()).isNotNull();
	}
}
