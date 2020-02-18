package be.task.spring_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class QuoteMapper implements RowMapper<Quote> {

    @Override
    public Quote mapRow(ResultSet resultSet, int i) throws SQLException {

        Quote quote = new Quote(
                resultSet.getString("isin"),
                resultSet.getFloat("bid"),
                resultSet.getInt("ask")
        );
        quote.setId(resultSet.getLong("id"));
        quote.setElvl(resultSet.getFloat("elvl"));
        return quote;
    }
}
