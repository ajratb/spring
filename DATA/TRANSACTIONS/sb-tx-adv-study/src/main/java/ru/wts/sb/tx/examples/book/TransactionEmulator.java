package ru.wts.sb.tx.examples.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unused")
@Component
public class TransactionEmulator {

    private BookDao bookDao;

    @Autowired
    TransactionEmulator(BookDao bookDao){
        this.bookDao = bookDao;
    }

    public void runTest() {
        runInTransaction();
    }

    @Transactional
    protected void runInTransaction() {
        bookDao.checkTitleDuplicate("title");
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
