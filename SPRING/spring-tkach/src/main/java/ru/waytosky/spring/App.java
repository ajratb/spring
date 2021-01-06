package ru.waytosky.spring;

/**
 *
 * @author Ayrat
 */
public class App {

	Client client;
	EventLogger eventLogger;

	public App(Client client, EventLogger eventLogger) {
		System.out.println("CONSTRUCTOR App");
		this.client = client;
		this.eventLogger = eventLogger;
	}

	void logEvent(Event event) {
		
       // String message=event.msg.replaceAll(client.getId(), client.getFullName()); 
		System.out.println("try to write event");
		eventLogger.logEvent(event);

	}
}
