package com.baeldung.boot.ddd.event;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;//use it instead of verifyZeroInteractions
//import static org.mockito.Mockito.verifyZeroInteractions; - it's deprecated

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.baeldung.boot.ddd.event.Aggregate1;
import com.baeldung.boot.ddd.event.Aggregate1Repository;
import com.baeldung.boot.ddd.event.DomainEvent;
import com.baeldung.boot.ddd.event.DomainService;

@SpringJUnitConfig
@SpringBootTest
class Aggregate1EventsIntegrationTest {

    @Autowired
    private DomainService domainService;

    @MockBean
    private TestEventHandler eventHandler;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private Aggregate1Repository repository;

    // @formatter:off
    @DisplayName("given existing aggregate,"
        + " when do domain operation directly on aggregate,"
        + " then domain event is NOT published")
    // @formatter:on
    @Test
    void aggregateEventsTest() {
        Aggregate1 existingDomainEntity = new Aggregate1(0, eventPublisher);
        repository.save(existingDomainEntity);

        // when
        repository.findById(existingDomainEntity.getId())
            .get()
            .domainOperation();

        // then
        verifyNoInteractions(eventHandler);
    }

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }

    // @formatter:off
    @DisplayName("given existing aggregate,"
        + " when do domain operation on service,"
        + " then domain event is published")
    // @formatter:on
    @Test
    void serviceEventsTest() {
        Aggregate1 existingDomainEntity = new Aggregate1(1, eventPublisher);
        repository.save(existingDomainEntity);

        // when
        domainService.serviceDomainOperation(existingDomainEntity.getId());

        // then
        verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));
    }

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public DomainService domainService(Aggregate1Repository repository, ApplicationEventPublisher eventPublisher) {
            return new DomainService(repository, eventPublisher);
        }
    }

}
