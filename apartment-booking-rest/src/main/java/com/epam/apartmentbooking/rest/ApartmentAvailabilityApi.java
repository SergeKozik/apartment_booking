package com.epam.apartmentbooking.rest;

import com.epam.apartmentbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/available", produces = "application/json")
public class ApartmentAvailabilityApi {

    @Autowired
    private BookingService bookingService;

    @ResponseBody
    @RequestMapping(value = "/time/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> checkTimeBooking (@PathVariable("id") int id,
                                                    @RequestParam("check_in") String checkInStr,
                                                    @RequestParam("check_out") String checkOutStr) {
        Boolean flag = bookingService.checkPeriodBooking(id,checkInStr,checkOutStr);
        return new ResponseEntity<String>(flag.toString(), HttpStatus.CREATED);
    }
}
