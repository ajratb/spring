package sb.jdbc.audited;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyVersionedUserRepositoryTest {

    @Autowired MyVersionedUserRepository myUserRepository;

    @Test
    void test(){
        MyVersionedUser sergey = new MyVersionedUser("Sergey");
        MyVersionedUser savedSergey = myUserRepository.save(sergey);
        assertThat(savedSergey).isNotNull();
        MyVersionedUser vasya = new MyVersionedUser("Vasya");
        MyVersionedUser savedVasya = myUserRepository.save(vasya);
        Iterable<MyVersionedUser> all = myUserRepository.findAll();
        List<MyVersionedUser> allUsers = new ArrayList<>();
        all.iterator().forEachRemaining(allUsers::add);
        assertThat(allUsers).hasSize(2);
        myUserRepository.delete(savedSergey);
        all = myUserRepository.findAll();
        allUsers.clear();
        all.iterator().forEachRemaining(allUsers::add);
        assertThat(allUsers).hasSize(2);
        Optional<MyVersionedUser> foundSergey = myUserRepository.findById(savedSergey.getId());
        assertThat(foundSergey.isPresent()).isTrue();
        Iterable<MyVersionedUser> byIdAndDeletedTrue = myUserRepository.findByDeletedTrue();
        allUsers.clear();
        byIdAndDeletedTrue.iterator().forEachRemaining(allUsers::add);
        assertThat(allUsers).isNotNull();
    }

}