package wts.spring.jdbc.booking;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
//

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JdbcConfig.class);

        BookingService bookingService = ctx.getBean(BookingService.class);
        bookingService.book("Alice", "Bob", "Carol");
        Assert.assertEquals("First booking should work with no problem", 3,
                bookingService.findAllBookings().size());

        try {
            bookingService.book("Chris", "Samuel");
        } catch (RuntimeException e) {
            log.info("v--- The following exception is expect because 'Samuel' is too big for the DB ---v");
            log.error(e.getMessage());
        }

        for (String person : bookingService.findAllBookings()) {
            log.info("So far, " + person + " is booked.");
        }
        log.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, and Chris was rolled back in the same TX");
        Assert.assertEquals("'Samuel' should have triggered a rollback", 3,
                bookingService.findAllBookings().size());

        try {
            bookingService.book("Buddy", null);
        } catch (RuntimeException e) {
            log.info("v--- The following exception is expect because null is not valid for the DB ---v");
            log.error(e.getMessage());
        }

        for (String person : bookingService.findAllBookings()) {
            log.info("So far, " + person + " is booked.");
        }
        log.info("You shouldn't see Buddy or null. null violated DB constraints, and Buddy was rolled back in the same TX");
        Assert.assertEquals("'null' should have triggered a rollback", 3, bookingService
                .findAllBookings().size());
    }

}
