package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.bean.BookingBookBean;
import com.epam.apartmentbooking.bean.BookingViewBean;
import com.epam.apartmentbooking.bean.UserProfileBean;
import com.epam.apartmentbooking.dao.BookingDao;
import com.epam.apartmentbooking.dao.PriceDao;
import com.epam.apartmentbooking.dao.SpaceDao;
import com.epam.apartmentbooking.entity.*;
import com.epam.apartmentbooking.service.BookingService;
import com.epam.apartmentbooking.bean.RoomTypeEnum;
import com.epam.apartmentbooking.service.dozer.EntityBeanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Serge_Kozik on 5/15/2017.
 */
@Service
@Transactional (isolation = Isolation.READ_COMMITTED)
public class BookingServiceImpl implements BookingService{

    private static final String DATE_FORMAT="dd-MM-YYYY";

    private DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private BookingDao bookingDao;
    private PriceDao priceDao;
    private SpaceDao spaceDao;
    private EntityBeanConverter converter;

    @Autowired
    public BookingServiceImpl(BookingDao bookingDao, PriceDao priceDao, SpaceDao spaceDao, EntityBeanConverter converter) {
        this.bookingDao = bookingDao;
        this.priceDao = priceDao;
        this.spaceDao = spaceDao;
        this.converter = converter;
        this.dateFormat.setTimeZone(TimeZone.getDefault());
    }

    @Override
    public List<BookingViewBean> showByUser(UserProfileBean profileBean) {
        if (profileBean==null) {
            return null;
        }
        return this.showByUser(profileBean.getId());
    }

    @Override
    public List<BookingViewBean> showByUser(int userId) {
        List<BookingEntity> entities = bookingDao.findByUserId(userId);
        if ((entities==null)||(entities.isEmpty())) {
            return null;
        }
        List<BookingViewBean> beans = converter.convertToBeanList(entities,BookingViewBean.class);
        return beans;
    }

    @Override
    public boolean bookApartment(BookingBookBean bookBean) {
        if ((bookBean==null)||(bookBean.getGuests()<1)) {
            return false;
        }
        int apartmentId=bookBean.getApartmentId();
        CriteriaTimeBookingDTO timeBookingEntity = converter.convertToEntity(bookBean,CriteriaTimeBookingDTO.class);
        if (bookingDao.checkPeriod(apartmentId,timeBookingEntity)) {
            BookingEntity bookingEntity = converter.convertToEntity(bookBean,BookingEntity.class);
            BigDecimal tmpAmount = this.calculatePrice(bookBean);
            bookingEntity.setTotalSum(tmpAmount);
            BookingEntity bookedEntity = bookingDao.book(bookingEntity);
            if (bookedEntity==null) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BigDecimal calculatePrice(BookingBookBean bookBean) {
        int apartmentId = bookBean.getApartmentId();
        PriceEntity priceEntity = priceDao.findByApartment_Id(apartmentId);
        SpaceEntity spaceEntity = spaceDao.findByApartment_Id(apartmentId);
        RoomTypeEnum roomType = RoomTypeEnum.getTypeEnum(spaceEntity.getRoomType());
        Date checkInDate = this.dateView2UtilDate(bookBean.getCheckIn());
        Date checkOutDate = this.dateView2UtilDate(bookBean.getCheckOut());
        long days=0;
        if ((checkInDate!=null)&&(checkOutDate!=null)) {
            long daysMillisec = checkOutDate.getTime()-checkInDate.getTime();
            days = TimeUnit.DAYS.convert(daysMillisec,TimeUnit.MILLISECONDS);
        }
        BigDecimal tmpAmount;
        if (roomType==RoomTypeEnum.SHARED_ROOM) {
            tmpAmount = priceEntity.getDaily().multiply(new BigDecimal(bookBean.getGuests()));
            tmpAmount = tmpAmount.multiply(new BigDecimal(days));
        } else {
            tmpAmount = priceEntity.getDaily().multiply(new BigDecimal(days));
        }
        return tmpAmount;
    }

    @Override
    public String date2PageViewFormat(Date date) {
        if (date==null) {
            return "--";
        }
        return this.dateFormat.format(date);
    }

    @Override
    public Date dateView2UtilDate(String date) {
        try {
            return this.dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public Boolean checkPeriodBooking(int apartmentId, String checkInStr, String checkOutStr) {
        Date checkInDate = this.dateView2UtilDate(checkInStr);
        Date checkOutDate = this.dateView2UtilDate(checkOutStr);
        if ((checkInDate==null)||(checkOutDate==null)) {
            return false;
        }
        CriteriaTimeBookingDTO timeBookingEntity = new CriteriaTimeBookingDTO(checkInDate,checkOutDate);
        return bookingDao.checkPeriod(apartmentId,timeBookingEntity);
    }
}
