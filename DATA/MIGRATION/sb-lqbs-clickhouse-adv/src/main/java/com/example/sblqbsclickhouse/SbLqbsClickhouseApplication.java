package com.example.sblqbsclickhouse;

import com.example.sblqbsclickhouse.clickhouse.db.entities.entities.ClickHouseEntity;
import com.example.sblqbsclickhouse.clickhouse.db.repo.ClickHouseRepository;
import com.example.sblqbsclickhouse.postgres.repo.PostgresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.util.Optional;

@Slf4j
@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SbLqbsClickhouseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SbLqbsClickhouseApplication.class, args);
	}

	@Autowired
	@Qualifier("clickhouseDataSource")
	DataSource dataSource;

	@Autowired
//	@Qualifier("clickHouseRepository")
	ClickHouseRepository clickHouseRep;
//	@Autowired
//	PostgresRepository postgresRep;

	public static int counter;
	@Override
	public void run(String...args) throws Exception {
		log.info("Increment counter");
		counter++;
		log.info("counter is: {}", counter);

//		Optional<String> advertiserNameByInn = clickHouseRep.getAdvertiserNameByInn(1L);
		long count = clickHouseRep.count();
		log.info("count is: {}", count);
		Optional<ClickHouseEntity> foundInClickhouse = clickHouseRep.findById(1L);
		foundInClickhouse.ifPresent(entity -> log.info("TITLE IS: {}", entity.getTitle()));
//		Optional<PostgresEntity> foundInPostgres = postgresRep.findById(1L);
//		foundInPostgres.ifPresent(entity -> log.info("TITLE IS: {}", entity.getGenreName()));
	}

}
