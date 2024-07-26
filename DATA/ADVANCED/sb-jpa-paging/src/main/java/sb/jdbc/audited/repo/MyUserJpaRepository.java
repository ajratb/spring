package sb.jdbc.audited.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sb.jdbc.audited.entity.MyUser;

public interface MyUserJpaRepository extends JpaRepository<MyUser, Integer> {
}
