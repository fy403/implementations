package com.practicalddd.cargotracker.bookingms.domain.builder;

import com.practicalddd.cargotracker.bookingms.domain.model.entities.Location;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.*;

public class DeliveryBuilder {
    private RoutingStatus routingStatus; //Routing Status of the Cargo
    private TransportStatus transportStatus; //Transport Status of the Cargo
    private Location lastKnownLocation;
    private Voyage currentVoyage;
    private LastCargoHandledEvent lastEvent;

    public DeliveryBuilder() {

    }
    public DeliveryBuilder withRoutingStatus(RoutingStatus routingStatus) {
        this.routingStatus = routingStatus;
        return this;
    }
    public DeliveryBuilder withTransportStatus(TransportStatus transportStatus) {
        this.transportStatus = transportStatus;
        return this;
    }
    public DeliveryBuilder withLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
        return this;
    }
    public DeliveryBuilder withCurrentVoyage(Voyage currentVoyage) {
        this.currentVoyage = currentVoyage;
        return this;
    }
    public DeliveryBuilder withLastEvent(LastCargoHandledEvent lastEvent) {
        this.lastEvent = lastEvent;
        return this;
    }
    public Delivery build() {
        return new Delivery(routingStatus, transportStatus, lastKnownLocation, currentVoyage, lastEvent);
    }
}
