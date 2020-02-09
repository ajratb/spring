package wts.spring.jdbc;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wts.spring.jdbc.model.Person;

/**
 *
 * @author ayrat
 */
public interface PersonDao {

    Person getPersonById(Long id);

    List<Person> getAllPersons();

    boolean deletePerson(Person person);

    @Transactional(propagation = Propagation.MANDATORY)
    boolean updatePerson(Person person);

    boolean createPerson(Person person);
}
