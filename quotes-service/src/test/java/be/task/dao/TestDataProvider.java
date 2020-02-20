package be.task.dao;

import static java.util.Arrays.*;
import java.util.List;

/**
 *
 * @author BikchentaevAA
 */
public class TestDataProvider {

    public static List<Quote> getQuotes() {
        return asList(new Quote("1234567890ab", 4.4, 5.5), new Quote("ba0987654321", 54, 123));
    }

    public static Quote createDummyQuote() {
        return new Quote("123456789000", 0, 1);
    }

    public static Quote createQuoteWithShortIsin() {
        return new Quote("12345678901", 0, 1);
    }

    public static Quote createQuoteWithLongIsin() {
        return new Quote("1234567890123", 0, 1);
    }

    public static Quote createQuoteWithBidBiggerThanAsk() {
        return new Quote("123456789000", 20.0099, 19.99999);
    }
}
