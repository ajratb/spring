package sb.jdbc.audited.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sb.jdbc.audited.entity.MyUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyUserCrudRepositoryTest {

    @Autowired
    MyUserCrudRepository myUserCrudRepository;

    @Test
    void test() {

        MyUser sergey = new MyUser("Sergey");
        MyUser savedSergey = myUserCrudRepository.save(sergey);
        assertThat(savedSergey).isNotNull();

        MyUser vasya = new MyUser("Vasya");
        MyUser savedVasya = myUserCrudRepository.save(vasya);

        Iterable<MyUser> foundAll = myUserCrudRepository.findAll();
        List<MyUser> foundUsers = new ArrayList<>();
        foundAll.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        myUserCrudRepository.delete(savedSergey);
        Optional<MyUser> foundSergey = myUserCrudRepository.findById(savedSergey.getId());
        assertThat(foundSergey.isPresent()).isTrue();

        foundAll = myUserCrudRepository.findAll();
        foundUsers.clear();
        foundAll.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        savedVasya.setVersion(5);
        MyUser updatedVasya = myUserCrudRepository.save(savedVasya);
        assertThat(updatedVasya).isNotNull();
    }

}