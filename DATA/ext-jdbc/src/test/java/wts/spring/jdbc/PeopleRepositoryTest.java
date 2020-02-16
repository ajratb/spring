package wts.spring.jdbc;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import wts.spring.jdbc.model.Person;

/**
 *
 * @author ayrat
 */
@RunWith(SpringRunner.class)
//@Transactional
@ContextConfiguration(classes = JdbcConfig.class)
public class PeopleRepositoryTest {

    @Autowired
    PersonDao personDao;

    @Test
    public void createSimplePerson() {

        Person person = new Person();
        
        person.setAge(25);
//        customer.dob = LocalDate.of(1904, 5, 14);
        person.setFirstName("Albert");
        person.setLastName("Schultz");
        boolean saved = personDao.createPerson(person);

        assertThat(saved).isTrue();//.isNotNull();

        List<Person> persons = personDao.getAllPersons();
        
        assertThat(persons).hasSize(2);
        
//        saved.firstName = "Hans Albert";
//
//        personDao.save(saved);
//
//        Optional<People> reloaded = personDao.findById(saved.id);
//
//        assertThat(reloaded).isNotEmpty();
//
//        assertThat(reloaded.get().firstName).isEqualTo("Hans Albert");
    }
}
