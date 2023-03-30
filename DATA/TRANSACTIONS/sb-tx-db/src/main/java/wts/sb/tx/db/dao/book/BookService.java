package wts.sb.tx.db.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import wts.sb.tx.db.entities.Book;

import java.sql.Date;
import java.time.LocalDate;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
public class BookService {
    public static class SomeException extends RuntimeException {}

    private final BookDaoCustom bookDao;

    //@Transactional(rollbackFor = SomeException.class)
    @SuppressWarnings("CommentedOutCode")
    @Transactional(noRollbackFor = RuntimeException.class)
    public void transactionWithException() {
        //bookService.neverCallInTransaction();
        //throw new SomeException();
        throw new RuntimeException();
    }

    @Transactional
    public void printLastBook() {
        Book b = bookDao.getDao().getLatestBook();
        System.out.println("Latest book is: " + (b == null ? "NULL" : b.getTitle()));
    }

    @Transactional(propagation = Propagation.NEVER)
    public void neverCallInTransaction() {
        System.out.println("It is long-long method.");
    }

    @Transactional
    public void testLogicalTransaction() {
        printLastBook();
        bookDao.addBook("Java", Date.valueOf(LocalDate.of(2015, 5, 1)));
        try {
            transactionWithException();
            // inside transaction, we get an exception,
            // and it is marking exception for rollback-only:
            // setRollbackOnly(true)
        } catch (RuntimeException e) {
            if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()) {
                throw new RuntimeException();
            }
        }

        System.out.println("****** Here we proceed...");
        printLastBook();
    }
}
