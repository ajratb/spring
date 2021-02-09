package sboot.data.jpa;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@Entity
public class Customer {

	@Transient
	private final Collection<DomainEvent> domainEvents;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;

	protected Customer() {
		domainEvents = new ArrayList<>();
	}

	public Customer(String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@AfterDomainEventPublication
	public void clearEvents() {
		domainEvents.clear();
	}

//	private int times = 1;
	public void domainOperation() {
		// some domain operation
//		if (times < 1)
//			times = 1;
//		else if (times > 3)
//			times = 3;
//		for (int i = 0; i < times; i++)
		domainEvents.add(new DomainEvent());
	}

	@DomainEvents
	public Collection<DomainEvent> events() {
		return domainEvents;
	}

}
