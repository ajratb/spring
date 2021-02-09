/**
 *
 */
package sboot.data.jpa;

import org.springframework.transaction.event.TransactionalEventListener;

interface TestEventHandler {
    @TransactionalEventListener
    void handleEvent(DomainEvent event);
}
