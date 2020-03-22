package wts.spring.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import wts.spring.jdbc.model.Person;
import wts.spring.jdbc.model.PersonMapper;

/**
 *
 * @author BikchentaevAA
 */
//@Component
@Repository//just for convention, may be better support for Exception translation
public class PersonDaoImpl implements PersonDao {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParamJdbc;//need to be initiated
    SimpleJdbcInsert simpleJdbcInsert;

    private final String SQL_FIND_PERSON = "select * from people where id = ?";
    private final String SQL_DELETE_PERSON = "delete from people where id = ?";
    private final String SQL_UPDATE_PERSON = "update people set first_name = ?, last_name = ?, age  = ? where id = ?";
    private final String SQL_GET_ALL = "select * from people";
    private final String SQL_INSERT_PERSON = "insert into people(first_name, last_name, age, range) values(?,?,?,?)";
    private final String SQL_SHORT_INSERT_PERSON = "insert into people values(?,?,?,?)";

    @Autowired
    public PersonDaoImpl(DataSource dataSource) {
        
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("people")
                .usingGeneratedKeyColumns("id");
        jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
    }

    @Override
    public Person getPersonById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_PERSON, new Object[]{id}, new PersonMapper());
    }

    @Override
    public List<Person> getAllPersons() {
//        return jdbcTemplate.query(SQL_GET_ALL, getPersonMapper());

        //<result_list>.forEach(customer -> log.info(customer.toString()));

        return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
    }
    
    //for entities with simple constructor OR you can use BUILDER
    private RowMapper<Person> getPersonMapper(){
        return (rs, rowNum) -> new Person(rs.getInt("id"),
                rs.getString("first_name"), rs.getString("last_name"),
                rs.getFloat("range"));
   //!!!             //.setAge(rs.getInt("age"));
    }

    @Override
    public boolean deletePerson(Person person) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, person.getId()) > 0;
    }

    public boolean updatePerson(Person person) {
        return jdbcTemplate.update(SQL_UPDATE_PERSON, person.getFirstName(), person.getLastName(), person.getAge(),
                person.getId()) > 0;
    }

    @Override
    public boolean createPerson(Person person) {
        // if id is not serial
        // SQL_INSERT_PERSON = "insert into people(id, first_name, last_name, age) values(?,?,?,?)";
        // return jdbcTemplate.update(SQL_INSERT_PERSON, person.getId(), person.getFirstName() ...
        return jdbcTemplate.update(SQL_INSERT_PERSON, person.getFirstName(), person.getLastName(),
                person.getAge(), person.getRange()) > 0;
    }

    public int getRowsCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM courses", Integer.class);
    }
    
    public int[] batchUpdateUsingNamedParametrJdbcTemplate(List<Person> persons) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(persons.toArray());
        int[] updateCounts = namedParamJdbc.batchUpdate(
                "INSERT INTO PEOPLE VALUES (:firstName, :lastName, :age, :range)", batch);
        return updateCounts;
    }

    public int[] batchInsert(List<Object[]> personsData) {
        return jdbcTemplate.batchUpdate(SQL_INSERT_PERSON, personsData);
    }
    
    @Override
     public int addPersonAndReturnKey(Person emp) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", emp.getId());
        parameters.put("FIRST_NAME", emp.getFirstName());
        parameters.put("LAST_NAME", emp.getLastName());
        parameters.put("AGE", emp.getAge());
        parameters.put("RANGE", emp.getRange());

//        return simpleJdbcInsert.execute(parameters);
        Number key = simpleJdbcInsert.executeAndReturnKey(parameters);
        return (int) key;
    }

    public int[] batchUpdateUsingJdbcTemplate(List<Person> persons) {
        return jdbcTemplate.batchUpdate(SQL_INSERT_PERSON,
                new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1, persons.get(i).getId());
                ps.setString(1, persons.get(i).getFirstName());
                ps.setString(2, persons.get(i).getLastName());
                ps.setInt(3, persons.get(i).getAge());
                ps.setFloat(4, persons.get(i).getRange());
            }

            @Override
            public int getBatchSize() {
                return 50;
            }
        });
    }

    Function<List<Person>, List<Object[]>> personsToDataArraysFunction() {
        return persons -> persons.stream().map(this::personToArray).collect(toList());
    }

    private Object[] personToArray(Person p) {
        return new Object[]{p.getFirstName(), p.getLastName(), p.getAge(), p.getRange()};
    }

    public int[] saveAll(List<Person> persons) {
        return batchInsert(personsToDataArraysFunction().apply(persons));
    }
}
