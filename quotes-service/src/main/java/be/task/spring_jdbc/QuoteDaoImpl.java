package be.task.spring_jdbc;

import java.util.List;
import java.util.function.Function;
import static java.util.stream.Collectors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QuoteDaoImpl implements QuoteDao {

    @Autowired
    JdbcTemplate jdbc;

    private final String SQL_FIND_QUOTE = "select * from quote where isin = ?";
    private final String SQL_INSERT_QUOTE = "insert into quote(isin, bid, ask, elvl) values(?,?,?,?)";

    @Override
    public int[] saveAll(List<Quote> quotes) {
        return batchInsert(funQuotesToArrays().apply(quotes));
    }

    private int[] batchInsert(List<Object[]> quotesData) {
        return jdbc.batchUpdate(SQL_INSERT_QUOTE, quotesData);
    }

    @Override
    public float getELevel(String isin) {
        Quote q = jdbc.queryForObject(SQL_FIND_QUOTE, new Object[]{isin}, new QuoteMapper());
        return q != null ? q.getElvl() : -1f;
    }

    Function<List<Quote>, List<Object[]>> funQuotesToArrays() {
        return quotes -> quotes.stream().map(this::quoteToArray).collect(toList());
    }

    Object[] quoteToArray(Quote q) {
        return new Object[]{q.getIsin(), q.getBid(), q.getAsk(), q.getElvl()};
    }

    /**
     * use it to check life state.
     *
     * @return rows count in database
     */
    int getRowsCount() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM quote", Integer.class);
    }
}
