package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

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

    public List<String> findAllBookings() {
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME"));
    }

}
