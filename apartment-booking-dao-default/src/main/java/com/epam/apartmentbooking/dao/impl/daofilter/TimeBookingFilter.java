package com.epam.apartmentbooking.dao.impl.daofilter;

import com.epam.apartmentbooking.dao.ApartmentDao;
import com.epam.apartmentbooking.dao.BookingDao;
import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;
import com.epam.apartmentbooking.entity.CriteriaTimeBookingDTO;

import java.util.List;

/**
 * Created by Roldo on 05.07.2017.
 */
public class TimeBookingFilter implements DaoFilterMarker {

    private BookingDao bookingDao;
    private ApartmentDao apartmentDao;

    public TimeBookingFilter(BookingDao bookingDao, ApartmentDao apartmentDao) {
        this.bookingDao = bookingDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    public List<Integer> filterApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaTimeBookingDTO criteriaTimeBookingDTO = criteriaApartmentDTO.getTimeBooking();
        if (criteriaTimeBookingDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = bookingDao.returnBusyApartmentIdByCriteria(criteriaTimeBookingDTO);
        if ((filteredIds!=null)&&(!filteredIds.isEmpty())) {
            apartmentIds.removeAll(filteredIds);
        }
        return apartmentIds;
    }

    @Override
    public List<Integer> addApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaTimeBookingDTO criteriaTimeBookingDTO = criteriaApartmentDTO.getTimeBooking();
        if (criteriaTimeBookingDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = bookingDao.returnBusyApartmentIdByCriteria(criteriaTimeBookingDTO);
        List<Integer> allIds = apartmentDao.showAllIds();
        if ((filteredIds!=null)&&(!filteredIds.isEmpty())&&(allIds!=null)) {
            allIds.removeAll(filteredIds);
            apartmentIds.addAll(allIds);
        }
        return apartmentIds;
    }
}
