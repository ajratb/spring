package wts.spring.jdbc;

import java.util.List;
import wts.spring.jdbc.model.Person;

/**
 *
 * @author ayrat
 */
public interface PersonDao {

    Person getPersonById(Long id);

    List<Person> getAllPersons();

    boolean deletePerson(Person person);

    boolean updatePerson(Person person);

    boolean createPerson(Person person);
}
