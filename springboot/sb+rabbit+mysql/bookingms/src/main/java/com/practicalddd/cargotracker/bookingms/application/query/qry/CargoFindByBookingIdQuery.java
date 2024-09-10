package com.practicalddd.cargotracker.bookingms.application.query.qry;

import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;

public class CargoFindByBookingIdQuery {
    private BookingId bookingId;

    public CargoFindByBookingIdQuery(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public BookingId getBookingId() {
        return bookingId;
    }
}
