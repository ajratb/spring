package ru.wts.sb.tx.examples.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.wts.sb.tx.examples.book.exception.DuplicateBookTitleException;
import ru.wts.sb.tx.examples.logging.Log;
import ru.wts.sb.tx.examples.logging.LogDao;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookDaoImpl implements BookDaoCustom {

    private final BookDao bookDao;
    private final LogDao logDao;

    @Transactional()
    @Override
    public void setPublishingHouse(String publishingHouse) {
        List<Book> all = bookDao.findAll();
        for (Book b : all) {
            b.setPublishingHouse(publishingHouse);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void checkTitleDuplicate(String title) {
        System.out.println("checkTitleDuplicate");
        long count = bookDao.findAll().stream().map(Book::getTitle).filter(t -> t.equals(title)).count();
        if (count > 0) {
            throw new DuplicateBookTitleException("book with title " + title + " already exists");
        }
    }

    @Override
    @Transactional
    public void addBook(String title, Date dateRelease) {
        logDao.log("adding book with name " + title);
        checkTitleDuplicate(title);
        bookDao.save(new Book(title, dateRelease));
    }

    @Override
    @Transactional(noRollbackFor = DuplicateBookTitleException.class)
    public void addBookNoRollback(String title, Date dateRelease) {
        logDao.save(new Log("adding log in method with no rollback for book " + title));
        checkTitleDuplicate(title);
        bookDao.save(new Book(title, dateRelease));
    }

    @Override
    @Transactional
    public void addLogs() {
        bookDao.save(new Book());
        //log.save(new Log("check before not supported"));
        logDao.addSeparateLogsNotSupported();
    }

    @Override
    @Transactional
    public void showLogs() {
        logDao.showLogs();
    }

}
