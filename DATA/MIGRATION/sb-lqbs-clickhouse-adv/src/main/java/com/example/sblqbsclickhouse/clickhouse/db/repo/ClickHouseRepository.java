package com.example.sblqbsclickhouse.clickhouse.db.repo;

import com.example.sblqbsclickhouse.clickhouse.db.entities.entities.ClickHouseEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClickHouseRepository extends CrudRepository<ClickHouseEntity, Long> {
/*
//    @Query(value = """
//            select Title as name
//            from simple
//            where WatchID = :inn
//          """)
//    public Optional<String> getAdvertiserNameByInn(Long inn);
 */
}

