package ru.wts.sboot.jdbc;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

	@Query("SELECT * FROM customers WHERE first_name = :firstName")
	List<Customer> findByFirstName(@Param("firstName") String firstName);
	
	@Modifying
	@Query("UPDATE customers SET first_name = :name WHERE id = :id")
	boolean updateByFirstName(@Param("id") Long id, @Param("name") String name);
}
