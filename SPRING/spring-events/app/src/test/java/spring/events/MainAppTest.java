package spring.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@RecordApplicationEvents
public class MainAppTest {
	@Autowired
	ApplicationEvents events;
	
	@Autowired
	CustomSpringEventPublisher publisher;

	@Test
	public void test() {
		
		publisher.publishCustomEvent("This is The Event Message");
		
		//it doesn't work
		//long numEvents = events.stream(CustomSpringEventPublisher.class).count();
		//assertEquals(numEvents, 0);
	}
}
