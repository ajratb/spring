/**
 *
 */
package com.baeldung.boot.ddd.event;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.baeldung.boot.ddd.event.Aggregate2;
import com.baeldung.boot.ddd.event.Aggregate2Repository;
import com.baeldung.boot.ddd.event.DomainEvent;

@SpringJUnitConfig
@SpringBootTest
class Aggregate2EventsIntegrationTest {
	@MockBean
	private TestEventHandler eventHandler;
	@Autowired
	private Aggregate2Repository repository;

	// @formatter:off
    @DisplayName("given aggregate with @AfterDomainEventPublication,"
        + " when do domain operation and save twice,"
        + " then an event is published only for the first time")
    // @formatter:on
	@Test
	void afterDomainEvents() {

		int times = 2;
		// given
		Aggregate2 aggregate = new Aggregate2(times);
		//now the collection is empty!

		// when
		aggregate.domainOperation();// for ${times} times:add event in the collecion
		// after saving, automatically: publish all events from collecion and clean it
		repository.save(aggregate);
		//this will break the test(so leave it commented):
		//aggregate.domainOperation();
		repository.save(aggregate);// try to publish, but there are no events in collection after all
		// do this so many times as you want - That's doesn't matter
		repository.save(aggregate);
		// ...

		// then
		verify(eventHandler, times(times)).handleEvent(any(DomainEvent.class));
	}

	@BeforeEach
	void beforeEach() {
		repository.deleteAll();
	}

	// @formatter:off
    @DisplayName("given aggregate with @DomainEvents,"
        + " when do domain operation and save,"
        + " then an event is published")
    // @formatter:on
	@Test
	void domainEvents() {
    	int times = 2;
		// given
		Aggregate2 aggregate = new Aggregate2(times);

		// when
		aggregate.domainOperation();
		repository.save(aggregate);

		// then
		verify(eventHandler, times(times)).handleEvent(any(DomainEvent.class));
	}

}
