package spring.examples.events_usage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @SpringJUnitConfig(SpringJUnitConfigTest.Config.class) is equivalent to:
 * 
 * @ExtendWith(SpringExtension.class)
 * @ContextConfiguration(classes = SpringJUnitConfigTest.Config.class )
 *
 */
@SpringJUnitConfig(JupiterTestExample.Config.class)
class JupiterTestExample {
	
	@Configuration
	static class Config{}
	
//	@Autowired
//	Message msg;
	@Autowired
	ApplicationContext context;

	
	@Test
	public void givenAppContext_WhenInjected_ThenItShouldNotBeNull() {
		//fail("Not yet implemented");
//		assertEquals("", msg.getVal());
		assertNotNull(context);
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
}
