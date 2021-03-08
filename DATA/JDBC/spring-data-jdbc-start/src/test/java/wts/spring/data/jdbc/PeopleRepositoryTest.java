package wts.spring.data.jdbc;

import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ayrat
 */
@RunWith(SpringRunner.class)
//@Transactional - do i need it here? try it when you know how to test transactions
@ContextConfiguration(classes = PeopleConfig.class)
public class PeopleRepositoryTest {

    @Autowired
    PeopleRepository customerRepo;

    @Test
    public void createSimplePerson() {

        People person = new People();
        
        person.age = 37;
//        customer.dob = LocalDate.of(1904, 5, 14);
        person.firstName = "Albert";
        person.lastName = "Schultz";
        assertThat(person.id).isNull();
        People saved = customerRepo.save(person);
        assertThat(person.id).isNotNull();//!!!
        assertThat(saved.id).isNotNull();

        saved.firstName = "Hans Albert";

        customerRepo.save(saved);

        Optional<People> reloaded = customerRepo.findById(saved.id);

        assertThat(reloaded).isNotEmpty();

        assertThat(reloaded.get().firstName).isEqualTo("Hans Albert");
    }
}
