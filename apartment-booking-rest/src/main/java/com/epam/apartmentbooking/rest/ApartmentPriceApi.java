package com.epam.apartmentbooking.rest;

import com.epam.apartmentbooking.bean.BookingBookBean;
import com.epam.apartmentbooking.service.BookingService;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roldo on 16.05.2017.
 */
@RestController
@RequestMapping(value = "/rest/price",produces = "application/json")
public class ApartmentPriceApi {

    @Autowired
    private BookingService bookingService;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getPrice(@PathVariable("id") int id,
                                                 @RequestParam("guests") int guests,
                                                 @RequestParam("check_in") String checkInStr,
                                                 @RequestParam("check_out") String checkOutStr) {
        BookingBookBean bookBean = new BookingBookBean();
        bookBean.setGuests(guests);
        bookBean.setCheckIn(checkInStr);
        bookBean.setCheckOut(checkOutStr);
        bookBean.setApartmentId(id);
        BigDecimal amount = bookingService.calculatePrice(bookBean);
        List<String> result = new ArrayList<>();
        result.add(amount.toString());
        result.add("USD");
        return new ResponseEntity<List<String>>(result,HttpStatus.CREATED);
    }
}
