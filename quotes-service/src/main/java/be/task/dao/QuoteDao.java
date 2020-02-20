package be.task.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ayrat
 */
public interface QuoteDao {

    int[] saveAll(List<Quote> quotes);

    double getElvl(String isin);

    Map<String, Double> getIsinToElvlMap();
    
    List<Quote> getAll();
}
