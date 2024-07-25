package sb.jdbc.audited;

import org.springframework.data.repository.CrudRepository;

public interface MyUserRepository extends CrudRepository<MyUser, Integer> {

    Iterable<MyUser> findByDeletedTrue();
    Iterable<MyUser> findByDeletedFalse();
}
