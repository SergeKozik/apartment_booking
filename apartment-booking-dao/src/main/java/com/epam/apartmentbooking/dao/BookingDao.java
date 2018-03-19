package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.BookingEntity;
import com.epam.apartmentbooking.entity.CriteriaTimeBookingDTO;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
public interface BookingDao {
    public List<Integer> returnBusyApartmentIdByCriteria(CriteriaTimeBookingDTO timeBookingEntity);
    public List<BookingEntity> findByUserId(int userId);
    public boolean checkPeriod(int apartmentId,CriteriaTimeBookingDTO timeBookingEntity);
    public BookingEntity book(BookingEntity bookingEntity);
}
