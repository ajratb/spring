package com.example.sb.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.TimeUnit;

/*
 * Uncompleted code from official github.
 * Almost the same as in Runner.
 * https://github.com/spring-guides/gs-messaging-rabbitmq/blob/main/complete/src/test/java/com/example/messagingrabbitmq/MessagingRabbitmqApplicationTest.java
 */
@SpringBootTest
@Slf4j
class SbRabbitApplicationTests {

	@SuppressWarnings("unused")
	@MockBean
	private Runner runner;

//	@Autowired
//	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Receiver receiver;

	@Value("${rabbitmq.queue}") String queue;

	/*
	 * The same as in Runner.
	 * But in convertAndSend set queue instead of topic
	 * No assertions yet.
	 */
	@Test
	public void test() throws Exception {
		try {
			System.out.println("Sending message in queue...");
			// here queue is being used
//			rabbitTemplate.convertAndSend(queue, "Hello from RabbitMQ!");
			boolean awaitResult = receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
			System.out.println("Runner finished successful: " + awaitResult);
			log.info("Runner finished successful: {}", awaitResult);
		} catch (AmqpConnectException e) {
			// ignore - rabbit is not running
		}
	}

	@Test
	void contextLoads() {
	}

}
