package com.practicalddd.cargotracker.bookingms.infrastructure.pubsub.subscriber;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.practicalddd.cargotracker.bookingms.domain.event.CargoBookedEvent;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.repository.CargoRepositoryMP;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class CargoRepositoryEventListen {
    @Autowired
    private EventBus eventBus;
    @Autowired
    private CargoRepositoryMP cargoRepositoryMP;

    @PostConstruct
    public void init(){
        eventBus.register(this);
    }


    @Subscribe
    public void handleCargoBookedEvent(CargoBookedEvent cargoBookedEvent){
        try {
            System.out.println("**** "+cargoBookedEvent+" Store");
            System.out.println("****" +cargoBookedEvent.getCargoBookedEventData());
            cargoRepositoryMP.save(cargoBookedEvent.getCargoBookedEventData().getCargo());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
