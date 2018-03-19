package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.bean.BookingBookBean;
import com.epam.apartmentbooking.bean.BookingViewBean;
import com.epam.apartmentbooking.bean.UserProfileBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Serge_Kozik on 5/15/2017.
 */
public interface BookingService {

    public List<BookingViewBean> showByUser(UserProfileBean profileBean);
    public List<BookingViewBean> showByUser(int userId);
    public boolean bookApartment(BookingBookBean bookBean);
    public BigDecimal calculatePrice(BookingBookBean bookBean);
    public String date2PageViewFormat(Date date);
    public Date dateView2UtilDate(String date);
    public Boolean checkPeriodBooking(int apartmentId, String checkInStr, String checkOutStr);
}
