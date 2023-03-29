package wts.sb.tx.db.dao.versionedbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wts.sb.tx.db.entities.VersionedBook;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VersionedBookManagerTest {

    @Autowired
    VersionedBookDao versionedBookDao;

    @Autowired
    private VersionedBookManager versionedBookManager;

    /*
     * testVersion.
     * 1. Add new book.
     * 2. Update it in one thread consequently.
     * 3. Update it in parallel threads with pessimistic lock.
     * (Concurrent update in table "VER_BOOK": another transaction has updated or deleted the same row [90131-214])
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void testVersion() throws Exception {

        int bookId = addBook();

        updateBook(bookId, "First-time updated title", 1);
        updateBook(bookId, "Second-time updated title", 1);

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            executorService.submit(() -> updateBook(bookId, "Third-time updated title", 5));
            Thread.sleep(200);
            executorService.submit(() -> updateBook(bookId, "Fourth-time updated title", 1));

            executorService.shutdown();
            boolean success = executorService.awaitTermination(15, TimeUnit.SECONDS);
            Assertions.assertTrue(success);
        }

        Optional<VersionedBook> book = versionedBookDao.findById(bookId);
        assertThat(book.get().getVersion()).isEqualTo(3L);
    }

    public int addBook() {
        System.out.println("+++++++++++++++++++++++++++");
        VersionedBook book = new VersionedBook();
        book.setTitle("New Book");
        System.out.println(Thread.currentThread().getName() + " - addBook: Book to save: [" + book + "]");
        book = versionedBookDao.save(book);
        System.out.println(Thread.currentThread().getName() + " - addBook: Saved Book: [" + versionedBookManager.readBook(book.getId()) + "]");
        System.out.println("---------------------------");
        return book.getId();
    }

    public void updateBook(Integer id, String newTitle, int operationTimeSec) {
        System.out.println("+++++++++++++++++++++++++++");
        try {
            versionedBookManager.updateBookWithLock(id, newTitle, operationTimeSec);
            System.out.println(Thread.currentThread().getName() + " - successfully committed.");
        } catch (RuntimeException e) {
            // TransactionSystemException: Could not roll back JPA transaction
            System.out.println(Thread.currentThread().getName() + " - runtime exception was caught, rollback was done.");
            System.out.println(Thread.currentThread().getName() + " - " + e.getClass().getSimpleName() + ": " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println(Thread.currentThread().getName() + " - undefined exception was caught, rollback was done.");
//            System.out.println("Thread.currentThread().getName() + \" - \" + e.getClass().getSimpleName() + \": \" + e.getMessage()");
        } finally {
            System.out.println(Thread.currentThread().getName() + " - current book version [" + versionedBookManager.readBook(1) + "]");
            System.out.println("---------------------------");
        }
    }

    /*
     * testLocks.
     * 1. Add new book and read it with and without locks in threads within transactions
     * 2. After removing book transactions with locks will get rollback (ObjectOptimisticLockingFailureException)
     */
    @Test
    public void testLocks() throws Exception {

        addBook();

        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            executorService.submit(() -> readBookAnPause(1));
            Thread.sleep(200);
            executorService.submit(() -> readBookWithLockAnPause(1));
            Thread.sleep(200);
            executorService.submit(() -> readBookWithLockAnPause(1));
            Thread.sleep(200);
            executorService.submit(() -> readBookAnPause(1));
            Thread.sleep(200);
            executorService.submit(() -> deleteBook(1));

            executorService.shutdown();
            boolean success = executorService.awaitTermination(30, TimeUnit.SECONDS);
            Assertions.assertTrue(success);
        }
    }

    public void readBookWithLockAnPause(Integer id) {
        System.out.println("+++++++++++++++++++++++++++");
        try {
            versionedBookManager.readBookWithLockAndPause(id, 5);
            System.out.println(Thread.currentThread().getName() + " - successfully committed.");
        } catch (RuntimeException e) {
            System.out.println(Thread.currentThread().getName() + " - runtime exception was caught, rollback was done.");
            System.out.println(Thread.currentThread().getName() + " - " + e.getClass().getSimpleName() + ": " + e.getMessage());
        } finally {
            System.out.println("---------------------------");
        }
    }

    public void readBookAnPause(Integer id) {
        System.out.println("+++++++++++++++++++++++++++");
        try {
            versionedBookManager.readBookAndPause(id, 5);
            System.out.println(Thread.currentThread().getName() + " - successfully committed.");
        } catch (RuntimeException e) {
            System.out.println(Thread.currentThread().getName() + " - runtime exception was caught, rollback was done.");
            System.out.println(Thread.currentThread().getName() + " - " + e.getClass().getSimpleName() + ": " + e.getMessage());
        } finally {
            System.out.println("---------------------------");
        }
    }

    public void deleteBook(Integer id) {
        System.out.println("+++++++++++++++++++++++++++");
        try {
            versionedBookManager.deleteBook(id);
            System.out.println(Thread.currentThread().getName() + " - successfully committed.");
        } catch (RuntimeException e) {
            System.out.println(Thread.currentThread().getName() + " - runtime exception was caught, rollback was done.");
            System.out.println(Thread.currentThread().getName() + " - " + e.getClass().getSimpleName() + ": " + e.getMessage());
        } finally {
            System.out.println(Thread.currentThread().getName() + " - current book version [" + versionedBookManager.readBook(1) + "]");
            System.out.println("---------------------------");
        }
    }

}
