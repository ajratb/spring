package ru.wts.sb.tx.examples.version;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class VersionedBookManagerTest {

    @Autowired
    VersionedBookDao versionedBookDao;

    @Autowired
    private VersionedBookManager versionedBookManager;

    public void addBook() {
        System.out.println("+++++++++++++++++++++++++++");
        VersionedBook book = new VersionedBook();
        book.setTitle("New Book");
        System.out.println(Thread.currentThread().getName() + " - addBook: Book to save: [" + book + "]");
        book = versionedBookDao.save(book);
        System.out.println(Thread.currentThread().getName() + " - addBook: Saved Book: [" + versionedBookManager.readBook(book.getId()) + "]");
        System.out.println("---------------------------");
    }

    public void updateBook(Integer id, String newTitle, int operationTimeSec) {
        System.out.println("+++++++++++++++++++++++++++");
        try {
            versionedBookManager.updateBookWithLock(id, newTitle, operationTimeSec);
            System.out.println(Thread.currentThread().getName() + " - successfully committed.");
        } catch (RuntimeException e) {
            System.out.println(Thread.currentThread().getName() + " - runtime exception was caught, rollback was done.");
            System.out.println(Thread.currentThread().getName() + " - " + e.getClass().getSimpleName() + ": " + e.getMessage());
        } finally {
            System.out.println(Thread.currentThread().getName() + " - current book version [" + versionedBookManager.readBook(1) + "]");
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

    @Test
    public void testVersion() throws Exception {

        addBook();

        updateBook(1, "First-time updated title", 1);
        updateBook(1, "Second-time updated title", 1);

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            executorService.submit(() -> updateBook(1, "Third-time updated title", 5));
            Thread.sleep(200);
            executorService.submit(() -> updateBook(1, "Fourth-time updated title", 1));

            executorService.shutdown();
            boolean success = executorService.awaitTermination(15, TimeUnit.SECONDS);
            Assertions.assertTrue(success);
        }
    }

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


}