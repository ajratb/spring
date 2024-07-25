package sb.jdbc.audited.repo;

import sb.jdbc.audited.entity.MyVersionedUser;

public interface MyVersionedUserRepository extends SoftDeleteCrudRepository<MyVersionedUser, Integer> {
}
