/**
 *
 */
package com.baeldung.boot.ddd.event;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * It's used for manipulation with Aggregate1(using agregate1Repository parameter).
 * 
 * @author ayrat
 *
 */
@Service
public class DomainService {
    private final ApplicationEventPublisher eventPublisher;
    private final Aggregate1Repository repository;

    public DomainService(Aggregate1Repository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void serviceDomainOperation(long entityId) {
        repository.findById(entityId)
            .ifPresent(entity -> {
//                entity.domainOperation();// it doesn't for the tests!
                repository.save(entity);
                eventPublisher.publishEvent(new DomainEvent());
            });
    }

}
