package sboot.jdbc.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * You can use this code in BookingService
 *
 * @author ayrat
 */

@Component
public class TransactionTest {

	private final static Logger logger = LoggerFactory.getLogger(TransactionTest.class);

	/**
	 * If propagation is NEVER you will get IllegalTransactionStateException
	 * 
	 * @param jdbcTemplate
	 * @param persons
	 */
//	@Transactional(propagation = Propagation.NEVER)
	public void canBeExecutedInTransaction(JdbcTemplate jdbcTemplate, String... persons) {

		for (String person : persons) {
			logger.info("Booking " + person + " in a seat...");
			jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
		}
	}
}
