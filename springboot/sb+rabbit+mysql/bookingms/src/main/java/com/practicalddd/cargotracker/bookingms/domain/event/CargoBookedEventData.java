package com.practicalddd.cargotracker.bookingms.domain.event;


import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;

/**
 * Event Data for the Cargo Booked Event
 */
public class CargoBookedEventData {

    private String bookingId;
    private Cargo cargo;
    public CargoBookedEventData(){}
    public CargoBookedEventData(String bookingId){ this.bookingId = bookingId; }
    public String getBookingId(){return this.bookingId;}
    public CargoBookedEventData(String bookingId, Cargo cargo) { this.bookingId = bookingId; this.cargo = cargo;}
    public Cargo getCargo(){return this.cargo;}
}
