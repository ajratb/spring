package sboot.data.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
//In CrudRepository: public Optional<T> findById(ID id);
//But you can rewrite this(without Optional): Customer findById(long id);
}