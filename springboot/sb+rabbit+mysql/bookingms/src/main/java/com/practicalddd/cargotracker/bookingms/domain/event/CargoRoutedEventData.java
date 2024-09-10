package com.practicalddd.cargotracker.bookingms.domain.event;


import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;

/**
 * Event Data for the Cargo Routed Event
 */
public class CargoRoutedEventData {

    private String bookingId;
    public CargoRoutedEventData(String bookingId){ this.bookingId = bookingId; }
    public void setBookingId(String bookingId){this.bookingId = bookingId;}
    public String getBookingId(){return this.bookingId;}
}
