package ru.wts.sboot.data.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class ApplicationTests {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
		assertThat(jdbcTemplate).isNotNull();
		assertNotNull(jdbcTemplate);
	}

}
