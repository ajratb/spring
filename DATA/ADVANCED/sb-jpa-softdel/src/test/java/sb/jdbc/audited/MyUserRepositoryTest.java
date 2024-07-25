package sb.jdbc.audited;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyUserRepositoryTest {

    @Autowired
    MyUserRepository myUserRepository;

    @Test
    void test() {

        MyUser sergey = new MyUser("Sergey");
        MyUser savedSergey = myUserRepository.save(sergey);
        assertThat(savedSergey).isNotNull();

        MyUser vasya = new MyUser("Vasya");
        MyUser savedVasya = myUserRepository.save(vasya);

        Iterable<MyUser> foundAll = myUserRepository.findAll();
        List<MyUser> foundUsers = new ArrayList<>();
        foundAll.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        myUserRepository.delete(savedSergey);

        Optional<MyUser> foundSergey = myUserRepository.findById(savedSergey.getId());
        assertThat(foundSergey.isPresent()).isTrue();

        foundAll = myUserRepository.findAll();
        foundUsers.clear();
        foundAll.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        savedVasya.setVersion(5);
        savedVasya.setDeleted(true);
        MyUser updatedVasya = myUserRepository.save(savedVasya);
        assertThat(updatedVasya).isNotNull();

        Iterable<MyUser> foundDeleted = myUserRepository.findByDeletedTrue();
        foundUsers.clear();
        foundDeleted.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        foundAll = myUserRepository.findAll();
        foundUsers.clear();
        foundAll.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        Iterable<MyUser> foundActive = myUserRepository.findByDeletedFalse();
        foundUsers.clear();
        foundActive.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).isEmpty();
    }

}