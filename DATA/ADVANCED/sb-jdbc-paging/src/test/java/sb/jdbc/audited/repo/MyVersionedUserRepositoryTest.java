package sb.jdbc.audited.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sb.jdbc.audited.entity.MyVersionedUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyVersionedUserRepositoryTest {

    @Autowired
    MyVersionedUserRepository myUserRepository;

    @Test
    void test() {

        MyVersionedUser sergey = new MyVersionedUser("Sergey");
        MyVersionedUser savedSergey = myUserRepository.save(sergey);
        assertThat(savedSergey).isNotNull();

        MyVersionedUser vasya = new MyVersionedUser("Vasya");
        MyVersionedUser savedVasya = myUserRepository.save(vasya);

        Iterable<MyVersionedUser> foundAll = myUserRepository.findAll();
        List<MyVersionedUser> foundUsers = new ArrayList<>();
        foundAll.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        // PREFER THIS WAY !
        myUserRepository.delete(savedSergey);

        Optional<MyVersionedUser> foundSergey = myUserRepository.findById(savedSergey.getId());
        assertThat(foundSergey.isPresent()).isTrue();

        foundAll = myUserRepository.findAll();
        foundUsers.clear();
        foundAll.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

//        savedVasya.setVersion(5); - OptimisticLockException
        savedVasya.setDeleted(true);
        MyVersionedUser updatedVasya = myUserRepository.save(savedVasya);
        assertThat(updatedVasya).isNotNull();

        Iterable<MyVersionedUser> foundDeleted = myUserRepository.findByDeletedTrue();
        foundUsers.clear();
        foundDeleted.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        foundAll = myUserRepository.findAll();
        foundUsers.clear();
        foundAll.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).hasSize(2);

        Iterable<MyVersionedUser> foundActive = myUserRepository.findByDeletedFalse();
        foundUsers.clear();
        foundActive.iterator().forEachRemaining(foundUsers::add);
        assertThat(foundUsers).isEmpty();
    }

}