package com.practicalddd.cargotracker.bookingms.infrastructure.pubsub.publisher;

public interface DomainEventPublisher {
    public void publish(Object event);
}
