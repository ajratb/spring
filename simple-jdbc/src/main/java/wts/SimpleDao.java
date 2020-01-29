package wts;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author BikchentaevAA
 */
@Component
public class SimpleDao {
    JdbcTemplate jdbcTemplate;

    public SimpleDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public int getRowsCount(){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) - 89 FROM t", Integer.class);
    }
}
