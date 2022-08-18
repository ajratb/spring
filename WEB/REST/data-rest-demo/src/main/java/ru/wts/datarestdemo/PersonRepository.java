package ru.wts.datarestdemo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends CrudRepository<Person, Long> { }
