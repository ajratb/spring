
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest()
class NotSpringBootTest {

	private static final Logger log = LoggerFactory.getLogger(NotSpringBootTest.class);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("== BEFORE ALL ==");
		log.trace("- TRACE LEVEL IS DISABLED !");
		log.debug("- DEBUG");
		log.info("- INFO");
		log.warn("- WARN");
		log.error("- ERROR");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		log.info("== AFTER ALL ==");
	}

	@BeforeEach
	void setUp() throws Exception {
		log.info("== BEFORE EACH ==");
	}

	@AfterEach
	void tearDown() throws Exception {
		log.info("== AFTER EACH ==");
	}

	@Test
	@DisplayName("some situation is set up," 
	+ " when do some logic,"
			+ " then get some result")
	void test() {
		log.info("== START TEST ==");
		assertThat(5).isEqualTo(5);
	}

}
