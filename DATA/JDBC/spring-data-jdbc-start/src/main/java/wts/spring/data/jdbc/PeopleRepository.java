package wts.spring.data.jdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
interface PeopleRepository extends CrudRepository<People, Long> {

}