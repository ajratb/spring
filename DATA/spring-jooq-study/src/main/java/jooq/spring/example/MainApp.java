package jooq.spring.example;

import jooq.generated.public_.tables.Author;
import jooq.generated.public_.tables.AuthorBook;
import jooq.generated.public_.tables.Book;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


/**
 * Hello world!
 */
@Component("mainApp")
public class MainApp {

    @Autowired
    private DSLContext dsl;

    Author author = Author.AUTHOR;
    Book book = Book.BOOK;
    AuthorBook authorBook = AuthorBook.AUTHOR_BOOK;

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceConfiguration.class)) {
            MainApp app = (MainApp) ctx.getBean("mainApp");
           // app.insertIntoDb();
            System.out.println(app.print());
        }
    }

    private void insertIntoDb(){
        dsl.insertInto(author)
                .set(author.ID, 31)
                .set(author.FIRST_NAME, "Herbert")
                .set(author.LAST_NAME, "Schildt")
                .execute();
        dsl.insertInto(book)
                .set(book.ID, 6)
                .set(book.TITLE, "A Beginner's Guide")
                .execute();
        dsl.insertInto(authorBook)
                .set(authorBook.AUTHOR_ID, 4)
                .set(authorBook.BOOK_ID, 4)
                .execute();
    }

    private String print(){
        Result<Record3<Integer, String, Integer>> result = dsl
                .select(author.ID, author.LAST_NAME, DSL.count())
                .from(author)
                .join(authorBook)
                .on(author.ID.equal(authorBook.AUTHOR_ID))
                .join(book)
                .on(authorBook.BOOK_ID.equal(book.ID))
                .groupBy(author.LAST_NAME)
                .fetch();
        return result.toString();
    }
}
