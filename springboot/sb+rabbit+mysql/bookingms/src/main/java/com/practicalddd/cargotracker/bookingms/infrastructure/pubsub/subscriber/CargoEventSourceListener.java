package com.practicalddd.cargotracker.bookingms.infrastructure.pubsub.subscriber;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.practicalddd.cargotracker.bookingms.infrastructure.brokers.CargoEventSource;
import com.practicalddd.cargotracker.bookingms.domain.event.CargoBookedEvent;
import com.practicalddd.cargotracker.bookingms.domain.event.CargoRoutedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 */
@Service
@EnableBinding(CargoEventSource.class)
public class CargoEventSourceListener {

    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void init(){
        eventBus.register(this);
    }

    @Autowired
    CargoEventSource cargoEventSource;

    //@Async
    @Subscribe
    public void handleCargoBookedEvent(CargoBookedEvent cargoBookedEvent){
        try {
            System.out.println("**** "+cargoBookedEvent);
            System.out.println("**** "+cargoBookedEvent.getCargoBookedEventData());
            cargoEventSource.cargoBooking().send(MessageBuilder.withPayload(cargoBookedEvent).build());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //@Async
    @Subscribe
    public void handleCargoRoutedEvent(CargoRoutedEvent cargoRoutedEvent){
        System.out.println("**** "+cargoRoutedEvent);
        System.out.println("**** "+cargoRoutedEvent.getCargoRoutedEventData());
        cargoEventSource.cargoRouting().send(MessageBuilder.withPayload(cargoRoutedEvent).build());
    }
}
