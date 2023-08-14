package com.example.sblqbsclickhouse.postgres.repo;

import com.example.sblqbsclickhouse.postgres.entities.PostgresEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresRepository extends CrudRepository<PostgresEntity, Long> { }
