package wts.sb.tx.db.dao.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookControllerTest {

    @Autowired
    BookController bookController;
    @Test
    public void testLogicalTransaction() {
        //try {
        bookController.testLogicalTransaction();
        //} catch (Exception e) {}
        bookController.printLastBook();
    }
}