package com.practicalddd.cargotracker.bookingms.infrastructure.pubsub.subscriber;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.practicalddd.cargotracker.bookingms.infrastructure.brokers.rabbitmq.CargoEventSource;
import com.practicalddd.cargotracker.bookingms.domain.event.CargoBookedEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoRoutedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 */
@Service
@EnableBinding(CargoEventSource.class)
public class CargoEventSourceListen {

    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void init(){
        eventBus.register(this);
    }

    CargoEventSource cargoEventSource;

    public CargoEventSourceListen(CargoEventSource cargoEventSource){
        this.cargoEventSource = cargoEventSource;
    }
    @Async
    @Subscribe
    public void handleCargoBookedEvent(CargoBookedEvent cargoBookedEvent){
        try {
            System.out.println("****"+cargoBookedEvent);
            System.out.println("****"+cargoBookedEvent.getCargoBookedEventData());
            cargoEventSource.cargoBooking().send(MessageBuilder.withPayload(cargoBookedEvent).build());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Async
    @Subscribe
    public void handleCargoRoutedEvent(CargoRoutedEvent cargoRoutedEvent){
        cargoEventSource.cargoRouting().send(MessageBuilder.withPayload(cargoRoutedEvent).build());
    }
}
