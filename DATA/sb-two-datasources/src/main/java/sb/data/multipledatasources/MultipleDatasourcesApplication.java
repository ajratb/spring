package sb.data.multipledatasources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sb.data.multipledatasources.clickhouse.ClickHouseEntity;
import sb.data.multipledatasources.clickhouse.ClickHouseRepository;
import sb.data.multipledatasources.postgres.Genre;
import sb.data.multipledatasources.postgres.PgRepo;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class MultipleDatasourcesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourcesApplication.class, args);
    }

    @Autowired
    ClickHouseRepository clickHouseRep;
    @Autowired
    PgRepo postgresRep;

    @Override
    public void run(String... args) throws Exception {

        Optional<Genre> foundInPostgres = postgresRep.findById(1L);
        foundInPostgres.ifPresent(entity -> log.info("TITLE IS: {}", entity.getGenreName()));

        long count = clickHouseRep.count();
        log.info("count is: {}", count);
        Optional<ClickHouseEntity> foundInClickhouse = clickHouseRep.findById(1L);
        foundInClickhouse.ifPresent(entity -> log.info("TITLE IS: {}", entity.getTitle()));
        log.info("==============  END  ================");
    }

}
