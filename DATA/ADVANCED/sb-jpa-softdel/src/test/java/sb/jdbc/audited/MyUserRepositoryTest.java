package sb.jdbc.audited;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyUserRepositoryTest {

    @Autowired MyUserRepository myUserRepository;

    @Test
    void test(){
        MyUser sergey = new MyUser("Sergey");
        MyUser savedSergey = myUserRepository.save(sergey);
        assertThat(savedSergey).isNotNull();
        MyUser vasya = new MyUser("Vasya");
        MyUser savedVasya = myUserRepository.save(vasya);
        Iterable<MyUser> all = myUserRepository.findAll();
        List<MyUser> allUsers = new ArrayList<>();
        all.iterator().forEachRemaining(allUsers::add);
        assertThat(allUsers).hasSize(2);
        myUserRepository.delete(savedSergey);
        all = myUserRepository.findAll();
        allUsers.clear();
        all.iterator().forEachRemaining(allUsers::add);
        assertThat(allUsers).hasSize(1);
        Optional<MyUser> foundSergey = myUserRepository.findById(savedSergey.getId());
        assertThat(foundSergey.isPresent()).isFalse();
        Iterable<MyUser> byIdAndDeletedTrue = myUserRepository.findByOrDeletedTrue();
        allUsers.clear();
        byIdAndDeletedTrue.iterator().forEachRemaining(allUsers::add);
        assertThat(allUsers).isNotNull();
    }

}