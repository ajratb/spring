package wts.sb.tx.db.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wts.sb.tx.db.dao.book.exception.DuplicateBookTitleException;
import wts.sb.tx.db.dao.logging.LogDaoCustom;
import wts.sb.tx.db.entities.Book;
import wts.sb.tx.db.entities.Log;

import java.sql.Date;
import java.util.List;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class BookDaoCustom {

    private final BookDao bookDao;
    private final LogDaoCustom logDaoCustom;

    @Transactional()
    public void setPublishingHouse(String publishingHouse) {
        List<Book> all = bookDao.findAll();
        for (Book b : all) {
            b.setPublishingHouse(publishingHouse);
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void checkTitleDuplicate(String title) {
        System.out.println("checkTitleDuplicate");
        long count = bookDao.findAll().stream().map(Book::getTitle).filter(t -> t.equals(title)).count();
        if (count > 0) {
            throw new DuplicateBookTitleException("book with title " + title + " already exists");
        }
    }

    @Transactional
    public void addBook(String title, Date dateRelease) {
        logDaoCustom.writeLogInNewTransaction("adding book with name " + title);
        checkTitleDuplicate(title);
        bookDao.save(new Book(title, dateRelease));
    }

    @Transactional(noRollbackFor = DuplicateBookTitleException.class)
    public void addBookNoRollback(String title, Date dateRelease) {
        logDaoCustom.getDao().save(new Log("adding log in method with no rollback for book " + title));
        checkTitleDuplicate(title);
        bookDao.save(new Book(title, dateRelease));
    }

    @Transactional
    public void addLogs() {
        bookDao.save(new Book());
        //log.save(new Log("check before not supported"));
        logDaoCustom.addSeparateLogsNotSupported();
    }

    @Transactional
    public void showLogs() {
        logDaoCustom.showLogs();
    }

    public BookDao getDao() {
        return bookDao;
    }

}
