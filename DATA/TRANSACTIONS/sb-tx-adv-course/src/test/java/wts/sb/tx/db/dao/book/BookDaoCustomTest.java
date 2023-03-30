package wts.sb.tx.db.dao.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.IllegalTransactionStateException;
import wts.sb.tx.db.dao.book.exception.DuplicateBookTitleException;
import wts.sb.tx.db.dao.logging.LogDaoCustom;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BookDaoCustomTest {

    @Autowired
    BookDaoCustom bookDao;
    @Autowired
    LogDaoCustom logDao;

    @BeforeEach
    void setUp() {
        bookDao.getDao().deleteAll();
        logDao.getDao().deleteAll();
    }

    @Test
    public void notSupported() {
        // executing in transaction:
        // addLogs is starting transaction, but addSeparateLogsNotSupported() suspends it
        assertThrows(RuntimeException.class, () -> bookDao.addLogs());
        // no transaction - first record is added in the log even after exception
        logDao.showLogs();
        assertThat(logDao.getDao().findAll()).hasSize(1);
    }

    @Test
    public void mandatory() {
        // get exception because checkTitleDuplicate can be executed only in transaction
        IllegalTransactionStateException exception =
                assertThrows(IllegalTransactionStateException.class, () -> bookDao.checkTitleDuplicate("Java"));
        assertThat(exception.getMessage())
                .isEqualTo("No existing transaction found for transaction marked with propagation 'mandatory'");
    }

    @Test
    public void never() {
        bookDao.addBook("Java", Date.valueOf(LocalDate.of(2015, 5, 1)));
        // it's safe to call showLogs from no transaction
        logDao.showLogs();

        // but prohibited to execute from transaction
        try {
            bookDao.showLogs();
        } catch(Exception e) {
            System.out.println("ERROR! "+e.getMessage());
        }
    }

    @Test
    public void requiresNew() {
        // requires new - log message is persisted in the logs even after exception
        // because it was added in the separate transaction
        System.out.println("---------------------------");

        bookDao.addBook("Java", Date.valueOf(LocalDate.of(2015, 5, 1)));
        bookDao.addBook("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
        bookDao.addBook("Spring Data", Date.valueOf(LocalDate.of(2016, 1, 1)));

        try {
            bookDao.addBook("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
        } catch (DuplicateBookTitleException e) {
            System.out.println("WARN: " + e.getMessage());
        }

        System.out.println("Logs: ");
        logDao.getDao().findAll().forEach(System.out::println);

        System.out.println("List of added books: ");
        bookDao.getDao().findAll().forEach(System.out::println);
    }

    @Test
    public void noRollback() {
        // no rollback - log message is persisted in the logs even after exception
        // because transaction was not rolled back
        bookDao.addBookNoRollback("Java", Date.valueOf(LocalDate.of(2015, 5, 1)));
        bookDao.addBookNoRollback("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
        bookDao.addBookNoRollback("Spring Data", Date.valueOf(LocalDate.of(2016, 1, 1)));

        try {
            bookDao.addBookNoRollback("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
        } catch (DuplicateBookTitleException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Logs: ");
        logDao.getDao().findAll().forEach(System.out::println);

        System.out.println("List of added books: ");
        bookDao.getDao().findAll().forEach(System.out::println);
    }
}
