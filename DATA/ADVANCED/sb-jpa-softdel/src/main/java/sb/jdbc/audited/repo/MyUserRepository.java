package sb.jdbc.audited.repo;

import sb.jdbc.audited.entity.MyUser;

public interface MyUserRepository extends SoftDeleteCrudRepository<MyUser, Integer> {
}
