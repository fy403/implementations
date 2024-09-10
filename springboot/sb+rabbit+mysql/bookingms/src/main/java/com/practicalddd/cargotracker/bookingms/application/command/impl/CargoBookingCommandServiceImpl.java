package com.practicalddd.cargotracker.bookingms.application.command.impl;

import com.practicalddd.cargotracker.bookingms.application.command.CargoBookingCommandService;
import com.practicalddd.cargotracker.bookingms.infrastructure.pubsub.publisher.DomainEventPublisher;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dao.CargoDao;
import com.practicalddd.cargotracker.bookingms.infrastructure.rpc.cargo.routing.ExternalCargoRoutingService;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.domain.cmd.BookCargoCommand;
import com.practicalddd.cargotracker.bookingms.domain.cmd.RouteCargoCommand;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.CargoItinerary;
import com.practicalddd.cargotracker.bookingms.domain.event.CargoBookedEvent;
import com.practicalddd.cargotracker.bookingms.domain.event.CargoBookedEventData;
import com.practicalddd.cargotracker.bookingms.domain.event.CargoRoutedEvent;
import com.practicalddd.cargotracker.bookingms.domain.event.CargoRoutedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;

/**
 * Application Service class for the Cargo Booking Commands
 */

@Service
public class CargoBookingCommandServiceImpl implements CargoBookingCommandService {
    @Autowired
//    private CargoRepository cargoRepository;
    private CargoDao cargoRepository;
    @Autowired
    private ExternalCargoRoutingService externalCargoRoutingService;
    @Autowired
    private DomainEventPublisher domainEventPublisher;
    /**
     * Service Command method to book a new Cargo
     * @return BookingId of the Cargo
     */

    public BookingId bookCargo(BookCargoCommand bookCargoCommand){

        String random = UUID.randomUUID().toString().toUpperCase();
        bookCargoCommand.setBookingId(random.substring(0, random.indexOf("-")));
        Cargo cargo = new Cargo(bookCargoCommand);
        cargoRepository.save(cargo);
        domainEventPublisher.publish(new CargoBookedEvent(
                new CargoBookedEventData(cargo.getBookingId().getBookingId())
            )
        );
        return new BookingId(bookCargoCommand.getBookingId());
    }

    /**
     * Service Command method to assign a route to a Cargo
     * @param routeCargoCommand
     */

    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand){
        System.out.println("Route Cargo command "+routeCargoCommand.getCargoBookingId());
        Cargo cargo = cargoRepository.findByBookingId(
                new BookingId(routeCargoCommand.getCargoBookingId())
        );
        CargoItinerary cargoItinerary = externalCargoRoutingService
                .fetchRouteForSpecification(cargo.getRouteSpecification());
        cargo.assignToRoute(cargoItinerary);
        cargoRepository.save(cargo);
        domainEventPublisher.publish(new CargoRoutedEvent(
                new CargoRoutedEventData(cargo.getBookingId().getBookingId())));
    }


}
