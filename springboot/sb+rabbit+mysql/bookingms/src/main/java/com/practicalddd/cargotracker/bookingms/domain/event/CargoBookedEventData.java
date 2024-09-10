package com.practicalddd.cargotracker.bookingms.domain.event;


import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;

/**
 * Event Data for the Cargo Booked Event
 */
public class CargoBookedEventData {

    private String bookingId;
    public CargoBookedEventData(String bookingId){ this.bookingId = bookingId; }
    public String getBookingId(){return this.bookingId;}
}
