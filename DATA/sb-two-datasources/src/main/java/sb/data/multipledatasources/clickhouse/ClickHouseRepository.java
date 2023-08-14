package sb.data.multipledatasources.clickhouse;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ClickHouseRepository extends CrudRepository<ClickHouseEntity, Long> {
}
