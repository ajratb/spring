package be.task.spring_jdbc;

import static java.util.Arrays.*;
import java.util.List;

/**
 *
 * @author BikchentaevAA
 */
public class TestDataProvider {
    
    static List<Quote> getQuotes(){
        return asList(new Quote("1234567890ab", 4.4f, 5.5f), new Quote("ba0987654321", 54, 123));
    }
}
