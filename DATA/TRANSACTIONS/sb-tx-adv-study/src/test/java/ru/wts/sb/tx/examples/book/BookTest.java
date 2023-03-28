package ru.wts.sb.tx.examples.book;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testOrigEquals(){
        Book book1 = new Book();
        Book book2 = new Book();
        boolean origEqualsResult = book1.equals(book2);
        assertTrue(origEqualsResult);
    }

    @Test
    void testOrigEquals2(){
        Book book1 = new Book();
        Book book2 = new Book();
        book2.setDateRelease(Date.valueOf(LocalDate.of(2023, 3, 19)));
        boolean origEqualsResult = book1.equals(book2);
        assertFalse(origEqualsResult);
    }

}