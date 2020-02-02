package wts.spring.data.jdbc;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ayrat
 */
interface PeopleRepository extends CrudRepository<People, Long> {

}