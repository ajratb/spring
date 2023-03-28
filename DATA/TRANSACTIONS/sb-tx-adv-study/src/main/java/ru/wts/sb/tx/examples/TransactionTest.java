package ru.wts.sb.tx.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * You can use this code in BookingService
 *
 * @author ayrat
 */

@SuppressWarnings("SqlNoDataSourceInspection")
@Slf4j
@Component
public class TransactionTest {

	/**
	 * If propagation is NEVER you will get IllegalTransactionStateException
	 * If not-supported then package of persons with second wrong value (Samuel) will be inserted partially (only first person - Chris)
	 */
	@Transactional(propagation = Propagation.NEVER)
	public void canBeExecutedInTransaction(JdbcTemplate jdbcTemplate, String... persons) {
		log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
		for (String person : persons) {
			log.info("Booking " + person + " in a seat...");
			jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
		}
		log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
	}
}
