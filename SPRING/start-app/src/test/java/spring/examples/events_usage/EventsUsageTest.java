package spring.examples.events_usage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EventsUsageTestConfiguration.class)
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
		
		
		
		//assertEquals(numEvents, 0);
	}
}
