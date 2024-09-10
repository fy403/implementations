package com.practicalddd.cargotracker.bookingms.application.query;

import com.practicalddd.cargotracker.bookingms.application.query.qry.CargoFindByBookingIdQuery;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;

import java.util.List;

public interface CargoBookingQueryService {
    /**
     * Find all Cargos
     * @return List<Cargo>
     */

    public List<Cargo> findAll();

    /**
     * List All Booking Identifiers
     * @return List<BookingId>
     */
    public List<BookingId> findAllBookingIds();

    /**
     * Find a specific Cargo based on its Booking Id
     * @param bookingId
     * @return Cargo
     */
    public Cargo find(CargoFindByBookingIdQuery cargoByBookingIdQuery);
}