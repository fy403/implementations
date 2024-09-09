package com.practicalddd.cargotracker.bookingms.infrastructure.rpc.cargo.routing.acl;

import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.CargoItinerary;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.RouteSpecification;

public interface ExternalCargoRoutingService {
        /**
         * The Booking Bounded Context makes an external call to the Routing Service of the Routing Bounded Context to
         * fetch the Optimal Itinerary for a Cargo based on the Route Specification
         * @param routeSpecification
         * @return
         */
        public CargoItinerary fetchRouteForSpecification(RouteSpecification routeSpecification);
}
