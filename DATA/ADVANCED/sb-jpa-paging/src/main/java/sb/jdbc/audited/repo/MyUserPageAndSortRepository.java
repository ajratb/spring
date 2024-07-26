package sb.jdbc.audited.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import sb.jdbc.audited.entity.MyUser;

public interface MyUserPageAndSortRepository extends PagingAndSortingRepository<MyUser, Integer> {
}
