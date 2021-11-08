package jooq.spring.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jooq.DSLContext;

import org.jooq.exception.DataAccessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import test.public_.tables.Author;
import test.public_.tables.AuthorBook;
import test.public_.tables.Book;

@SpringJUnitConfig(PersistenceConfiguration.class)
public class MainAppTest {
    @Autowired
    private DSLContext dsl;


    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(dsl != null);

        Author author = test.public_.tables.Author.AUTHOR;
        test.public_.tables.Book book = Book.BOOK;
        AuthorBook authorBook = AuthorBook.AUTHOR_BOOK;
        dsl.insertInto(author)
                .set(author.ID, 4)
                .set(author.FIRST_NAME, "Herbert")
                .set(author.LAST_NAME, "Schildt")
                .execute();

    }
//    @Test(expected = DataAccessException.class)
//    public void givenInvalidData_whenInserting_thenFail() {
//        dsl.insertInto(authorBook)
//                .set(authorBook.AUTHOR_ID, 4)
//                .set(authorBook.BOOK_ID, 5)
//                .execute();
//    }
}
