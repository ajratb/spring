package events.baeldung;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import events.EventsTestConfiguration;

@SpringJUnitConfig(EventsTestConfiguration.class)
@RecordApplicationEvents
public class CustomSpringEventPublisherTest {

	@Autowired
	ApplicationEvents events;

	@Autowired
	CustomSpringEventPublisher publisher;

	@Test
	public void test() {

		int count = ThreadLocalRandom.current().nextInt(1, 12);
		IntStream.range(0, count).forEach(this::publishEvent);
		// publisher.publishCustomEvent("This is The Event Message");
		// orderService.submitOrder(new Oreder(...  OrderSubmitted event
		long numEvents = events.stream(CustomSpringEvent.class).count();
		assertEquals(count, numEvents);
	}
	
	private void publishEvent(int i) {
		publisher.publishCustomEvent("This is The Event Message");
	}
}
