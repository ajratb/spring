package ru.wts.datarestdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataRestDemoApplicationTests {

	@Autowired PersonRepository personRepository;
	@Test
	void contextLoads() {
		assertThat(personRepository).isNotNull();
	}

}
