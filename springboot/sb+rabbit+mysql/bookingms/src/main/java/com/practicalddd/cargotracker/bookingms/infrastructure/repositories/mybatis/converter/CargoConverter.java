package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.converter;

import com.practicalddd.cargotracker.bookingms.domain.builder.CargoBuilder;
import com.practicalddd.cargotracker.bookingms.domain.builder.DeliveryBuilder;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.domain.model.entities.Location;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.*;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.CargoDO;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.LegDO;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CargoConverter {

    public static CargoDO serialize(Cargo cargo) {
        if (cargo == null){
            return null;
        }
        CargoDO target = new CargoDO();
        target.setBookingId(cargo.getBookingId().getBookingId());
        target.setOriginId(cargo.getOrigin().getUnLocCode());
        target.setBookingAmount(cargo.getBookingAmount().getBookingAmount());
        // Legs
        List<LegDO> legs = cargo.getItinerary().getLegs().stream().map(LegConverter::serialize).collect(Collectors.toList());
        target.setLegs(legs);
        // RouteSpecification
        target.setSpecOriginId(cargo.getRouteSpecification().getOrigin().getUnLocCode());
        target.setSpecDestinationId(cargo.getRouteSpecification().getDestination().getUnLocCode());
        target.setSpecArrivalDeadline(cargo.getRouteSpecification().getArrivalDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        // Delivery
        target.setTransportStatus(cargo.getDelivery().getTransportStatus().name());
        target.setRoutingStatus(cargo.getDelivery().getRoutingStatus().name());
        target.setHandlingEventId(cargo.getDelivery().getLastEvent().getHandlingEventId());
        target.setNextExpectedLocationId(cargo.getDelivery().getLastKnownLocation().getUnLocCode());
        target.setNextExpectedHandlingEventType(cargo.getDelivery().getLastEvent().getHandlingEventType());
        target.setLastKnownLocationId(cargo.getDelivery().getLastKnownLocation().getUnLocCode());
        target.setNextExpectedVoyageId(cargo.getDelivery().getCurrentVoyage().getVoyageId());
        target.setCurrentVoyageNumber(cargo.getDelivery().getCurrentVoyage().getVoyageId());
        target.setLastHandlingEventVoyage(cargo.getDelivery().getCurrentVoyage().getVoyageId());
        target.setLastHandlingEventLocation(cargo.getDelivery().getLastEvent().getHandlingEventLocation());
        return target;
    }

    public static Cargo deserialize(CargoDO cargo) {
        if (cargo == null){
            return null;
        }
        BookingId bookingId = new BookingId(cargo.getBookingId());
        Location origin = new Location(cargo.getOriginId());
        Location destination = new Location(cargo.getSpecDestinationId());
        BookingAmount bookingAmount = new BookingAmount(cargo.getBookingAmount());
        // Legs
        CargoItinerary itinerary = new CargoItinerary(cargo.getLegs().stream().map(LegConverter::unserialize).collect(Collectors.toList()));
        Date arrivalDeadline =  Date.from(cargo.getSpecArrivalDeadline().atStartOfDay(ZoneId.systemDefault()).toInstant());
        // RouteSpecification
        RouteSpecification routeSpecification = new RouteSpecification(origin, destination, arrivalDeadline);
        // Delivery
        Delivery delivery = new DeliveryBuilder().
                withCurrentVoyage(new Voyage(cargo.getCurrentVoyageNumber())).
                withLastEvent(new LastCargoHandledEvent(cargo.getHandlingEventId(), cargo.getLastHandlingEventType(),cargo.getLastHandlingEventLocation(), cargo.getLastHandlingEventVoyage())).
                withLastKnownLocation(new Location(cargo.getLastKnownLocationId())).
                withRoutingStatus(RoutingStatus.valueOf(cargo.getRoutingStatus())).
                withTransportStatus(TransportStatus.valueOf(cargo.getTransportStatus())).build();
        return new CargoBuilder().
                withBookingId(bookingId).
                withBookingAmount(bookingAmount).
                withOrigin(origin).
                withRouteSpecification(routeSpecification).
                withItinerary(itinerary).
                withDelivery(delivery).
                build();
    }

}
