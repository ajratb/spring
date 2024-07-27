package sb.jdbc.audited.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sb.jdbc.audited.entity.MyVersionedUser;

public interface MyVersionedUserRepository extends
        SoftDeleteCrudRepository<MyVersionedUser, Integer>,
        JpaSpecificationExecutor<MyVersionedUser> {
}
