package com.practicalddd.cargotracker.bookingms.interfaces.rest;

import com.practicalddd.cargotracker.bookingms.application.command.CargoBookingCommandService;
import com.practicalddd.cargotracker.bookingms.application.query.CargoBookingQueryService;
import com.practicalddd.cargotracker.bookingms.application.query.qry.CargoFindByBookingIdQuery;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.interfaces.rest.dto.BookCargoResource;
import com.practicalddd.cargotracker.bookingms.interfaces.rest.transform.BookCargoCommandDTOAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping("/cargobooking")
public class CargoBookingController {

    @Autowired
    private CargoBookingCommandService cargoBookingCommandService; // Application Service Dependency
    @Autowired
    private CargoBookingQueryService cargoBookingQueryService;

    /**
     * Provide the dependencies
     * @param cargoBookingCommandService
     */
    public CargoBookingController(CargoBookingCommandService cargoBookingCommandService, CargoBookingQueryService cargoBookingQueryService){
        this.cargoBookingCommandService = cargoBookingCommandService;
        this.cargoBookingQueryService = cargoBookingQueryService;
    }

    /**
     * POST method to book a cargo
     * @param bookCargoResource
     */

    @PostMapping
    @ResponseBody
    public BookingId bookCargo(@RequestBody  BookCargoResource bookCargoResource){
        System.out.println("****Cargo Booked ****"+bookCargoResource.getBookingAmount());
        BookingId bookingId  = cargoBookingCommandService.bookCargo(
                BookCargoCommandDTOAssembler.toCommandFromDTO(bookCargoResource));

        return bookingId;
    }

    /**
     * GET method to retrieve a Cargo
     * @param bookingId
     * @return
     */
    @GetMapping("/findCargo")
    @ResponseBody
    public Cargo findByBookingId(@RequestParam("bookingId") String bookingId){
        return cargoBookingQueryService.find(new CargoFindByBookingIdQuery(new BookingId(bookingId)));
    }
}
