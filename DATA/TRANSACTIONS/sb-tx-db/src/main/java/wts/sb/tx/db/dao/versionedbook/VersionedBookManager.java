package wts.sb.tx.db.dao.versionedbook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wts.sb.tx.db.entities.VersionedBook;

import static java.util.concurrent.TimeUnit.SECONDS;

@SuppressWarnings({"unused", "UnusedReturnValue"})
@Slf4j
@RequiredArgsConstructor
@Component
public class VersionedBookManager {

    private final VersionedBookDao versionedBookDao;

    /******************************
     ***** See testVersion ********
     ******************************/

    @Transactional(timeout = 10, propagation = Propagation.REQUIRES_NEW)
    public VersionedBook updateBookWithLock(Integer id, String title, int operationTimeSec) {

        System.out.println(Thread.currentThread().getName() + " - updateBookWithLock: trying to read book...");

        //VersionedBook book = versionedBookDao.findWithOptimisticLockById(id);
        VersionedBook book = versionedBookDao.findWithPessimisticWriteLockById(id);
        //VersionedBook book = versionedBookDao.findWithPessimisticReadLockById(id);

        System.out.println(Thread.currentThread().getName() + " - updateBookWithLock: read book is [" + book + "] " + book.hashCode());
        pause(operationTimeSec);
        book.setTitle(title);

        System.out.println(Thread.currentThread().getName() + " - updateBookWithLock: saving changes ...");
        book = versionedBookDao.save(book);
        System.out.println(Thread.currentThread().getName() + " - done.");
        System.out.println(Thread.currentThread().getName() + " - updateBookWithLock: committing transaction ...");
        return book;
    }

    @Transactional
    public VersionedBook readBook(Integer id) {
        return versionedBookDao.findWithOptimisticLockById(id);
    }

    /******************************
     ***** See testLocks ********
     ******************************/

    @SuppressWarnings("CommentedOutCode")
    @Transactional
    public VersionedBook readBookWithLockAndPause(Integer id, int pauseSec) {
        System.out.println(Thread.currentThread().getName() + " - readBookWithLockAndPause: trying to read book...");
        //VersionedBook book = versionedBookDao.findWithPessimisticReadLockById(id);
        //VersionedBook book = versionedBookDao.findWithPessimisticWriteLockById(id);
        VersionedBook book = versionedBookDao.findWithOptimisticLockById(id);
        System.out.println(Thread.currentThread().getName() + " - readBookWithLockAndPause: read book is [" + book + "] " + book.hashCode());
        pause(pauseSec);
        System.out.println(Thread.currentThread().getName() + " - readBookWithLockAndPause: committing transaction ...");
        return book;
    }

    @Transactional
    public VersionedBook readBookAndPause(Integer id, int pauseSec) {
        System.out.println(Thread.currentThread().getName() + " - readBookAndPause: trying to read book...");
        VersionedBook book = versionedBookDao.getReferenceById(id);
        System.out.println(Thread.currentThread().getName() + " - readBookAndPause: read book is [" + book + "] " + book.hashCode());
        pause(pauseSec);
        System.out.println(Thread.currentThread().getName() + " - readBookAndPause: committing transaction ...");
        return book;
    }

    @Transactional
    public void deleteBook(Integer id) {
        System.out.println(Thread.currentThread().getName() + " - deleteBook: trying to read book...");
        //VersionedBook book = versionedBookDao.findWithPessimisticReadLockById(id);
        VersionedBook book = versionedBookDao.findWithPessimisticWriteLockById(id);
        System.out.println(Thread.currentThread().getName() + " - deleteBook: read book is [" + book + "] " + book.hashCode());

        System.out.println(Thread.currentThread().getName() + " - deleteBook: trying to delete book...");
        versionedBookDao.deleteById(id);
        System.out.println(Thread.currentThread().getName() + " - done.");
        System.out.println(Thread.currentThread().getName() + " - deleteBook: committing transaction ...");
    }

    private void pause(int sec) {
        try {
            Thread.sleep(SECONDS.toMillis(sec));
        } catch (Exception e) {
            log.error("",e);
        }
    }
}
