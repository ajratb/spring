package sboot.data.rest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//to change export details from default (/persons) to /people
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
//for default value @RepositoryRestResource can be ommited
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    
  List<Person> findByLastName(@Param("name") String name);

}