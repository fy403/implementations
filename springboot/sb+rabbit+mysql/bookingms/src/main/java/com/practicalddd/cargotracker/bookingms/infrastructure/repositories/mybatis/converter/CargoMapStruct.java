package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.converter;

import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.CargoDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {LegConverter.class})
public interface CargoMapStruct {

    CargoMapStruct INSTANCE = Mappers.getMapper(CargoMapStruct.class);

    @Mapping(source = "bookingId.bookingId", target = "bookingId")
    @Mapping(source = "origin.unLocCode", target = "originId")
    @Mapping(source = "bookingAmount.bookingAmount", target = "bookingAmount")
    @Mapping(source = "itinerary.legs", target = "legs")
    @Mapping(source = "routeSpecification.origin.unLocCode", target = "specOriginId")
    @Mapping(source = "routeSpecification.destination.unLocCode", target = "specDestinationId")
    @Mapping(source = "routeSpecification.arrivalDeadline", target = "specArrivalDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "delivery.routingStatus.name", target = "routingStatus")
    @Mapping(source = "delivery.transportStatus.name", target = "transportStatus")
    @Mapping(source = "delivery.lastEvent.handlingEventId", target = "handlingEventId")
    @Mapping(source = "delivery.lastEvent.handlingEventType", target = "nextExpectedHandlingEventType")
    @Mapping(source = "delivery.lastEvent.handlingEventLocation", target = "lastHandlingEventLocation")
    @Mapping(source = "delivery.lastKnownLocation.unLocCode", target = "lastKnownLocationId")
    @Mapping(source = "delivery.currentVoyage.voyageId", target = "currentVoyageNumber")
    CargoDO toCargoDO(Cargo cargo);

    @Mapping(source = "bookingId", target = "bookingId.bookingId")
    @Mapping(source = "originId", target = "origin.unLocCode")
    @Mapping(source = "specDestinationId", target = "routeSpecification.destination.unLocCode")
    @Mapping(source = "bookingAmount", target = "bookingAmount.bookingAmount")
    @Mapping(source = "legs", target = "itinerary.legs")
    @Mapping(source = "specArrivalDeadline", target = "routeSpecification.arrivalDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "routingStatus", target = "delivery.routingStatus")
    @Mapping(source = "transportStatus", target = "delivery.transportStatus")
    @Mapping(source = "handlingEventId", target = "delivery.lastEvent.handlingEventId")
    @Mapping(source = "nextExpectedHandlingEventType", target = "delivery.lastEvent.handlingEventType")
    @Mapping(source = "lastHandlingEventLocation", target = "delivery.lastEvent.handlingEventLocation")
    @Mapping(source = "lastKnownLocationId", target = "delivery.lastKnownLocation.unLocCode")
    @Mapping(source = "currentVoyageNumber", target = "delivery.currentVoyage.voyageId")
    Cargo toCargo(CargoDO cargoDO);
}