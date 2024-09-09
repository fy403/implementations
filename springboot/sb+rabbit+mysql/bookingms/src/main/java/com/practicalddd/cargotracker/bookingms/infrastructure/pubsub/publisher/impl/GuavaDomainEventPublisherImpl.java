package com.practicalddd.cargotracker.bookingms.infrastructure.pubsub.publisher.impl;

import com.google.common.eventbus.EventBus;
import com.practicalddd.cargotracker.bookingms.infrastructure.pubsub.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuavaDomainEventPublisherImpl implements DomainEventPublisher {

    @Autowired
    EventBus eventBus;

    public void publish(Object event) {
        eventBus.post(event);
    }

}