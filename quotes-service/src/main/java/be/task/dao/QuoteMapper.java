package be.task.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class QuoteMapper implements RowMapper<Quote> {

    @Override
    public Quote mapRow(ResultSet resultSet, int i) throws SQLException {

        Quote quote = new Quote(
                resultSet.getString("isin"),
                resultSet.getDouble("bid"),
                resultSet.getDouble("ask")
        );
        quote.setId(resultSet.getLong("id"));
        quote.setElvl(resultSet.getDouble("elvl"));
        return quote;
    }
}
