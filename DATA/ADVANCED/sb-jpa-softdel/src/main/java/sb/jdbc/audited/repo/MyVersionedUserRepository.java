package sb.jdbc.audited.repo;

import org.springframework.data.repository.CrudRepository;
import sb.jdbc.audited.entity.MyVersionedUser;

public interface MyVersionedUserRepository extends CrudRepository<MyVersionedUser, Integer> {

    Iterable<MyVersionedUser> findByDeletedTrue();

    Iterable<MyVersionedUser> findByDeletedFalse();
}
