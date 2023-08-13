package com.example.sblqbsclickhouse;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClickHouseRepository extends CrudRepository<ClickHouseEntity, Long> {
}

