package sb.jdbc.audited.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sb.jdbc.audited.entity.MyUser;

public interface MyUserWithJpaSpecRepository extends JpaSpecificationExecutor<MyUser> {
}
