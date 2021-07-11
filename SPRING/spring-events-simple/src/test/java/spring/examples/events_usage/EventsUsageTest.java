package spring.examples.events_usage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(EventsUsageTestConfiguration.class)
@RecordApplicationEvents
public class EventsUsageTest {

	@Autowired
	ApplicationEvents events;
	
	@Autowired
	CustomSpringEventPublisher publisher;

	@Test
	public void test() {
		
		publisher.publishCustomEvent("This is The Event Message");
		long numEvents = events.stream(CustomSpringEventPublisher.class).count();
		assertEquals(numEvents, 0);
	}
}
