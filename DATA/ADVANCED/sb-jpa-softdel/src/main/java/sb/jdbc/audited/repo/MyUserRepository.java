package sb.jdbc.audited.repo;

import org.springframework.data.repository.CrudRepository;
import sb.jdbc.audited.entity.MyUser;

public interface MyUserRepository extends CrudRepository<MyUser, Integer> {

    Iterable<MyUser> findByDeletedTrue();

    Iterable<MyUser> findByDeletedFalse();
}
