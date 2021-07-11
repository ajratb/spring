package spring.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedCustomSpringEventListener{

	@EventListener
	public void onApplicationEvent(CustomSpringEvent event) {
		System.out.println("@EventListener method handled an event: " + event.getMessage()); 

	}

}
