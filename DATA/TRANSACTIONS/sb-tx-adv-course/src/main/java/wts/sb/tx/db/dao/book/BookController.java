package wts.sb.tx.db.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wts.sb.tx.db.entities.Book;

import java.sql.Date;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class BookController {

    private final BookService bookService;

    private final BookDaoCustom bookDao;

    @SuppressWarnings("unused")
    @Transactional(noRollbackFor = BookService.SomeException.class)
    public void testLogicalTransaction() {

        printLastBook();
        bookDao.addBook("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
        //try {
            bookService.transactionWithException();
            // inside transaction, we get an exception,
            // and it is marking exception for rollback-only:
            // setRollbackOnly(true)
        //} catch (BookService.SomeException e) {
       // }
        System.out.println("****** Here we proceed...");
        Book b = bookDao.getDao().getLatestBook();
        b.setTitle("Updated");
        bookDao.getDao().saveAndFlush(b);
    }

    public void printLastBook() {
        bookService.printLastBook();
    }
}
