package ru.wts.sb.tx.examples;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

//@Primary
@SuppressWarnings("CommentedOutCode")
@Slf4j
@Component
//("tr")
public class TransactionsAppRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(TransactionsAppRunner.class);

    private final BookingService bookingService;

    public TransactionsAppRunner(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) {
        log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
        bookingService.book("Alice", "Bob", "Carol");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "First booking should work with no problem");        
        logger.info("Alice, Bob and Carol have been booked");
        log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
        try {
            bookingService.book("Chris", "Samuel");
        } catch (DataIntegrityViolationException e) {//RuntimeException
            logger.info("v--- The following exception is expect because 'Samuel' is too "
                    + "big for the DB ---v");
            logger.info("! {} : {} !", e.getClass(), e.getMessage());
        }
        log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
        for (String person : bookingService.findAllBookings()) {
            logger.info("So far, " + person + " is booked.");
        }
        logger.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, "
                + "and Chris was rolled back in the same TX");
//        Assert.isTrue(bookingService.findAllBookings().size() == 3,
//                "'Samuel' should have triggered a rollback");

//        try {
//            bookingService.book("Buddy", null);
//        } catch (DataIntegrityViolationException e) {//RuntimeException
//            logger.info("v--- The following exception is expect because null is not "
//                    + "valid for the DB ---v");
//            logger.info("! {} : {} !", e.getClass(), e.getMessage());
////            logger.error(e.getMessage());
//        }
//
//        for (String person : bookingService.findAllBookings()) {
//            logger.info("So far, " + person + " is booked.");
//        }
//        logger.info("You shouldn't see Buddy or null. null violated DB constraints, and "
//                + "Buddy was rolled back in the same TX");
//        Assert.isTrue(bookingService.findAllBookings().size() == 3,
//                "'null' should have triggered a rollback");
    }

}
