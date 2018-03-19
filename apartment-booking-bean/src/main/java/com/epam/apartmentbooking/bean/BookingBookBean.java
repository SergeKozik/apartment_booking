package com.epam.apartmentbooking.bean;

import com.epam.apartmentbooking.bean.validation.DateConstraint;
import com.epam.apartmentbooking.bean.validation.GuestsConstraint;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by Roldo on 16.05.2017.
 */
public class BookingBookBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Min(value = 1)
    private int apartmentId;

    private int userId;

    @DateConstraint
    private String checkIn;

    @DateConstraint
    private String checkOut;

    @GuestsConstraint
    private int guests;

    public BookingBookBean() {
    }

    public BookingBookBean(int apartmentId, int userId, String checkIn, String checkOut, int guests) {
        this.apartmentId = apartmentId;
        this.userId = userId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guests = guests;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingBookBean that = (BookingBookBean) o;

        if (apartmentId != that.apartmentId) return false;
        if (userId != that.userId) return false;
        if (guests != that.guests) return false;
        if (checkIn != null ? !checkIn.equals(that.checkIn) : that.checkIn != null) return false;
        return checkOut != null ? checkOut.equals(that.checkOut) : that.checkOut == null;

    }

    @Override
    public int hashCode() {
        int result = apartmentId;
        result = 31 * result + userId;
        result = 31 * result + (checkIn != null ? checkIn.hashCode() : 0);
        result = 31 * result + (checkOut != null ? checkOut.hashCode() : 0);
        result = 31 * result + guests;
        return result;
    }
}
