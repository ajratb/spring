package events.baeldung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher {
	@Autowired
	private ApplicationEventPublisher appPublisher;
	
	public void publishCustomEvent(final String message) {
		System.out.println("Publish custom event. ");
		CustomSpringEvent event = new CustomSpringEvent(this, message);
		appPublisher.publishEvent(event);
	}
}
