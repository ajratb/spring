package ru.wts.sboot.data.jdbc.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author ayrat
 */
public class TransactionTest {
    private final static Logger logger = LoggerFactory.getLogger(TransactionTest.class);
    public void isItTransactional(JdbcTemplate jdbcTemplate, String... persons) {
        
        for (String person : persons) {
            logger.info("Booking " + person + " in a seat...");
            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
        }
    }
}
