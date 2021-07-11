package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stuff.MyBean;

@SpringBootTest
class DemoApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	MyBean myBean;

	@Test
	void contextLoads() {
		log.debug("DEBUG LEVEL  IS DISABLED FROM SPRING");
		log.info("My Bean name is: {}!", myBean.getBeanName());
		assertNotNull(myBean);//using jupiter
		assertThat(myBean).isNotNull();//using assertj
		assertThat(myBean.getBeanName()).isEqualTo("Bar Bean");
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("== BEFORE ALL ==");
		log.trace("- TRACE LEVEL IS DISABLED !");
		log.debug("- DEBUG");
		log.info("- INFO");
		log.warn("- WARN");
		log.error("- ERROR");
	}
}
