package ru.wts.sb.tx.examples.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class BookController {

    private final BookService bookService;

    private final BookDao bookDao;

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
        Book b = bookDao.getLatestBook();
        b.setTitle("Updated");
        bookDao.saveAndFlush(b);
    }

    public void printLastBook() {
        bookService.printLastBook();
    }
}
