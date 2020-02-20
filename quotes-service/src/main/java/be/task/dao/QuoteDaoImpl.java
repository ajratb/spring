package be.task.dao;

import be.task.ApplicationRunner;
import be.task.api.ElvlNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QuoteDaoImpl implements QuoteDao {

    @Autowired
    JdbcTemplate jdbc;

    private static final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);

    private final String SQL_FIND_MAXID = "select max(id) from quote where isin = ?";
    private final String SQL_FIND_QUOTE = "select * from quote where isin = ? and id = ?";
    private final String SQL_INSERT_QUOTE = "insert into quote(isin, bid, ask, elvl) values(?,?,?,?)";
    private final String SQL_FIND_ALL = "select * from quote";
    private final String SQL_GET_ISIN_LIST = "select distinct isin from quote";

    @Override
    public int[] saveAll(List<Quote> quotes) {
        return batchInsert(funQuotesToArrays().apply(quotes));
    }

    private int[] batchInsert(List<Object[]> quotesData) {
        return jdbc.batchUpdate(SQL_INSERT_QUOTE, quotesData);
    }

    @Override
    public double getElvl(String isin) {

        Long maxId = jdbc.queryForObject(SQL_FIND_MAXID, new Object[]{isin}, Long.class);

        if (maxId == null) {
            throw new ElvlNotFoundException(isin);
        }

        Quote q = jdbc.queryForObject(SQL_FIND_QUOTE, new Object[]{isin, maxId}, new QuoteMapper());

        return q.getElvl();
    }

    Function<List<Quote>, List<Object[]>> funQuotesToArrays() {
        return quotes -> quotes.stream().map(this::quoteToArray).collect(toList());
    }

    Object[] quoteToArray(Quote q) {
        return new Object[]{q.getIsin(), q.getBid(), q.getAsk(), q.getElvl()};
    }

    @Override
    public List<Quote> getAll() {
        
        List<Quote> quotes = jdbc.query(SQL_FIND_ALL, new QuoteMapper());
        return quotes;
    }

    @Override
    public Map<String, Double> getIsinToElvlMap() {

        Map<String, Double> map = new HashMap<>();

        List<String> isinList = jdbc.query(SQL_GET_ISIN_LIST, new IsinMapper());

        for (String isin : isinList) {
            map.put(isin, getElvl(isin));
        }
        
        return map;
    }

}
