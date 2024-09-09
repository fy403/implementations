package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.repository;

import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;

public interface CargoRepositoryMP {
    Cargo findByBookingId(BookingId bookingId);
    void save(Cargo cargo);
}