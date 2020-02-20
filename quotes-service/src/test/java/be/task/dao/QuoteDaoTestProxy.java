package be.task.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QuoteDaoTestProxy {

    @Autowired
    private QuoteDao dao;
    @Autowired
    private JdbcTemplate jdbc;

    public int[] saveAll(List<Quote> quotes) {
        return dao.saveAll(quotes);
    }

    public double getELevel(String isin) {
        return dao.getElvl(isin);
    }

    public int getRowsCount() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM quote", Integer.class);
    }
}
