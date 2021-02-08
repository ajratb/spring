/**
 *
 */
package com.baeldung.boot.ddd.event;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@Entity
public class Aggregate2 {
	@Transient
	private final Collection<DomainEvent> domainEvents;
	@Id
	@GeneratedValue
	private long id;

	private int times;

	private Aggregate2() {
		domainEvents = new ArrayList<>();
	}

	public Aggregate2(int atLeastOneButNoMoreThenThree) {
		domainEvents = new ArrayList<>();
		this.times = atLeastOneButNoMoreThenThree;
	}

	@AfterDomainEventPublication
	public void clearEvents() {
		domainEvents.clear();
	}

	public void domainOperation() {
		// some domain operation
		if (times < 1)
			times = 1;
		else if (times > 3)
			times = 3;
		for (int i = 0; i < times; i++)
			domainEvents.add(new DomainEvent());
	}

	@DomainEvents
	public Collection<DomainEvent> events() {
		return domainEvents;
	}

}
