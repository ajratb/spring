package sb.jpa.criteria;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends
        CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
