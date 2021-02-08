/**
 *
 */
package com.baeldung.boot.ddd.event;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.context.ApplicationEventPublisher;

@Entity
class Aggregate1 {
    @Transient
    private ApplicationEventPublisher eventPublisher;
    @Id
    private long id;

    private Aggregate1() {
    }

    Aggregate1(long id, ApplicationEventPublisher eventPublisher) {
        this.id = id;
        this.eventPublisher = eventPublisher;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DomainEntity [id=" + id + "]";
    }

    //it's not working this way
    void domainOperation() {
        // some business logic
        if (eventPublisher != null) {
            eventPublisher.publishEvent(new DomainEvent());
        }
    }

    long getId() {
        return id;
    }

}
