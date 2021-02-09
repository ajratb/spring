package sboot.data.jpa;

import static org.assertj.core.api.Assertions.assertThat;

//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//import com.baeldung.boot.ddd.event.Aggregate2;
//import com.baeldung.boot.ddd.event.DomainEvent;

//import sboot.data.jpa.CustomerRepository;

//@SpringJUnitConfig
@SpringBootTest()
public class CustomerRepositoryIntegrationTest {

	private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryIntegrationTest.class);

	@Autowired
	private CustomerRepository repository;

	@BeforeEach
	void beforeEach() {
		log.info("BEFORE_EACH");
		repository.deleteAll();
	}

	// @formatter:off
    @DisplayName("situation,"
        + " when do some,"
        + " then one records in repository")
    // @formatter:on
	@Test
	void domainEvents() {

		Customer customer = new Customer("Jessy", "Lee");
		log.warn("CUSTOMER ID IS: {}", customer.getId());

		// when
		long count = repository.count();

		log.error("COUNT IS: {}", count);
		customer = repository.save(customer);
		count = repository.count();

		log.info("COUNT IS: {}", count);
		log.info("CUSTOMER ID IS: {}", customer.getId());

		assertThat(count).isEqualTo(1);
		assertThat(customer.getId()).isNotNull();

		// then
		// verify(eventHandler, times(times)).handleEvent(any(DomainEvent.class));
	}

}
