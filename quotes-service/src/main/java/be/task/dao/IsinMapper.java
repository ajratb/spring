package be.task.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ayrat
 */
public class IsinMapper implements RowMapper<String>{

    @Override
    public String mapRow(ResultSet rs, int i) throws SQLException {
        return rs.getString("isin");
    }

}
