package com.practicalddd.cargotracker.bookingms.application.query.impl;


import com.practicalddd.cargotracker.bookingms.application.query.CargoBookingQueryService;
import com.practicalddd.cargotracker.bookingms.application.query.qry.CargoFindByBookingIdQuery;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dao.CargoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@Service
public class CargoBookingQueryServiceImpl implements CargoBookingQueryService {

    @Autowired
    private CargoDao cargoDao;
    /**
     * Find all Cargos
     * @return List<Cargo>
     */

    public List<Cargo> findAll(){
        return cargoDao.queryCargos();
    }

    /**
     * List All Booking Identifiers
     * @return List<BookingId>
     */
   public List<BookingId> findAllBookingIds(){
        return cargoDao.selectAllBookingIds().stream().map(BookingId::new).collect(Collectors.toList());
   }

    /**
     * Find a specific Cargo based on its Booking Id
     * @param bookingId
     * @return Cargo
     */
    public Cargo find(CargoFindByBookingIdQuery cargoByBookingIdQuery){
        return cargoDao.selectByBookingId(cargoByBookingIdQuery.getBookingId().getBookingId());
    }
}
