package ru.wts.sb.tx.examples;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@SuppressWarnings("CommentedOutCode")
@Slf4j
@RequiredArgsConstructor
@Component
public class BookingService {

    private final JdbcTemplate jdbcTemplate;

    private final TransactionTest test;
    
    @Transactional
    public void book(String... persons) {
        log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
        test.canBeExecutedInTransaction(jdbcTemplate, persons);
//        for (String person : persons) {
//            logger.info("Booking " + person + " in a seat...");
//            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
//        }
        log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
    }

    @SuppressWarnings("SqlNoDataSourceInspection")
    public List<String> findAllBookings() {
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME"));
    }

}
