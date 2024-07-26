package sb.jdbc.audited.repo;

import org.springframework.data.repository.CrudRepository;
import sb.jdbc.audited.entity.MyUser;

public interface MyUserCrudRepository extends CrudRepository<MyUser, Integer> {
}
