package ru.wts.sboot.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@Transactional
//@ContextConfiguration(classes = JdbcTestConfiguration.class)
public class CustomerJdbcRepositoryIntegrationTests {

	private static final Logger log = LoggerFactory.getLogger(CustomerJdbcRepositoryIntegrationTests.class);

	@Autowired
	CustomerRepository repository;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void testCustomersTable() {

		log.info("inside test!!!");
		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" ")).collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		log.info("Querying for customer records where first_name = 'Josh':");
		repository.findByFirstName("Josh").forEach(customer -> log.info(customer.toString()));

		boolean updated = repository.updateByFirstName(1L, "Jenny");
		assertThat(updated).isTrue();
		Customer jenny = repository.findById(1L).orElse(new Customer("not_jenny", ""));
		assertThat(jenny.getFirstName()).isEqualTo("Jenny");

	}
	
	@Autowired
	FooBarRepository fooBarRepo;

	@Test
	public void testFooBarTable() {
		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE foo_Baar IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE foo_Baar(id SERIAL, name VARCHAR(255))");
//		jdbcTemplate.execute("DROP TABLE foo_bar IF EXISTS");
//		jdbcTemplate.execute("CREATE TABLE foo_bar(id SERIAL, name VARCHAR(255))");
		
		FooBar saved = fooBarRepo.save(new FooBar("alex"));
		log.info("saved foobar id: {}", saved.getId());
		
	}
}
