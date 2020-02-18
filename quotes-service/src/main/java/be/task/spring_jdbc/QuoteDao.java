package be.task.spring_jdbc;

import java.util.List;

/**
 *
 * @author ayrat
 */
public interface QuoteDao {

    int[] saveAll(List<Quote> quotes);

    float getELevel(String isin);
}
