package sb.data.multipledatasources.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface PgRepo extends JpaRepository<Genre, Long> {
}
