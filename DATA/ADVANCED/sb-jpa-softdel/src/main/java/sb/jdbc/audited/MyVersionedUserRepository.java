package sb.jdbc.audited;

import org.springframework.data.repository.CrudRepository;

public interface MyVersionedUserRepository extends CrudRepository<MyVersionedUser, Integer> {

    Iterable<MyVersionedUser> findByDeletedTrue();
    Iterable<MyVersionedUser> findByDeletedFalse();
}
