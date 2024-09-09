package com.practicalddd.cargotracker.bookingms.domain.builder;

import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.domain.model.entities.Location;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.BookingAmount;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.CargoItinerary;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.Delivery;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.RouteSpecification;

public class CargoBuilder {
    private Long id;
    private BookingId bookingId; // Aggregate Identifier
    private BookingAmount bookingAmount; //Booking Amount
    private Location origin; //Origin Location of the Cargo
    private RouteSpecification routeSpecification; //Route Specification of the Cargo
    private CargoItinerary itinerary; //Itinerary Assigned to the Cargo
    private Delivery delivery; // Checks the delivery progress of the cargo against the actual Route Specification and Itinerary
    public CargoBuilder withBookingId(BookingId bookingId){
        this.bookingId = bookingId;
        return this;
    }
    public CargoBuilder withBookingAmount(BookingAmount bookingAmount){
        this.bookingAmount = bookingAmount;
        return this;
    }
    public CargoBuilder withLocation(Location location){
        this.origin = location;
        return this;
    }
    public CargoBuilder withRouteSpecification(RouteSpecification routeSpecification){
        this.routeSpecification = routeSpecification;
        return this;
    }
    public CargoBuilder withOrigin(Location origin){
        this.origin = origin;
        return this;
    }
    public CargoBuilder withItinerary(CargoItinerary itinerary){
        this.itinerary = itinerary;
        return this;
    }
    public CargoBuilder withDelivery(Delivery delivery){
        this.delivery = delivery;
        return this;
    }

    public Cargo build(){
        return new Cargo(bookingId,bookingAmount,origin,routeSpecification,itinerary,delivery);
    }
}
