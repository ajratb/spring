package sboot.data.jpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SpringTest {
	
	private static final Logger log = LoggerFactory.getLogger(SpringTest.class);

	@Test
	void test() {
//		fail("Not yet implemented");
		// DISABLED:
		log.trace("TRACE");
		
	// ENABLED
		log.debug("DEBUG");
		log.info("INFO");
		log.warn("WARN");
		log.error("ERROR");
	}

}
