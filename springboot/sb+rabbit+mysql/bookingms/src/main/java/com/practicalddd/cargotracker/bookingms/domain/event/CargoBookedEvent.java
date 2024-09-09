package com.practicalddd.cargotracker.bookingms.domain.event;


/**
 * Event Class for the Cargo Booked Event. Wraps up the Cargo Booking identifier
 * for the event
 */

public class CargoBookedEvent {
    private CargoBookedEventData cargoBookedEventData;
    public CargoBookedEvent(){}
    public CargoBookedEvent(CargoBookedEventData cargoBookedEventData){
        this.cargoBookedEventData  = cargoBookedEventData;
    }

    public CargoBookedEventData getCargoBookedEventData() {
        return cargoBookedEventData;
    }
}
