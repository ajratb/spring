package wts.spring.jdbc;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import wts.spring.jdbc.model.Person;

/**
 *
 * @author ayrat
 */
@Component
public class SimpleJdbcUsage {

    SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public SimpleJdbcUsage(DataSource dataSource) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("people").usingGeneratedKeyColumns("id");;
    }

    public int addPerson(Person emp) {
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", emp.getId());
        parameters.put("FIRST_NAME", emp.getFirstName());
        parameters.put("LAST_NAME", emp.getLastName());
        parameters.put("AGE", emp.getAge());
        parameters.put("RANGE", emp.getRange());
        
//        return simpleJdbcInsert.execute(parameters);
        Number key = simpleJdbcInsert.executeAndReturnKey(parameters);
        return (int)key;
    }
}
