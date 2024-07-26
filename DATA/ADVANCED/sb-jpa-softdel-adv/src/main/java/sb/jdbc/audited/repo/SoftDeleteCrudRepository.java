package sb.jdbc.audited.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import sb.jdbc.audited.entity.BaseEntity;

@NoRepositoryBean
public interface SoftDeleteCrudRepository<T extends BaseEntity, ID extends Integer> extends CrudRepository<T, ID> {

    Iterable<T> findByDeletedTrue();

    Iterable<T> findByDeletedFalse();
}
