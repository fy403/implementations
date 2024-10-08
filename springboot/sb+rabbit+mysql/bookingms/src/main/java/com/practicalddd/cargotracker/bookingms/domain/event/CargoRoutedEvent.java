package com.practicalddd.cargotracker.bookingms.domain.event;


/**
 * Event Class for the Cargo Routed Event. Wraps up the Cargo
 */

public class CargoRoutedEvent {
    private CargoRoutedEventData cargoRoutedEventData;
    public CargoRoutedEvent(){}
    public CargoRoutedEvent(CargoRoutedEventData cargoRoutedEventData){
        this.cargoRoutedEventData = cargoRoutedEventData;
    }
    public CargoRoutedEventData getCargoRoutedEventData() {
        return this.cargoRoutedEventData;
    }
}