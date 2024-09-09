package com.practicalddd.cargotracker.bookingms.application.command;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.cmd.BookCargoCommand;
import com.practicalddd.cargotracker.bookingms.domain.cmd.RouteCargoCommand;

public interface CargoBookingCommandService {
    /**
     * Service Command method to book a new Cargo
     * @return BookingId of the Cargo
     */

    public BookingId bookCargo(BookCargoCommand bookCargoCommand);
    /**
     * Service Command method to assign a route to a Cargo
     * @param routeCargoCommand
     */
    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand);
}