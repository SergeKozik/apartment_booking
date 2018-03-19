package com.epam.apartmentbooking.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
public class CriteriaTimeBookingDTO implements CriteriaDTOMarker, Serializable {

    private static final long serialVersionUID = 1L;

    private Date checkIn;
    private Date checkOut;

    public CriteriaTimeBookingDTO() {
    }

    public CriteriaTimeBookingDTO(Date checkIn, Date checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaTimeBookingDTO that = (CriteriaTimeBookingDTO) o;

        if (checkIn != null ? !checkIn.equals(that.checkIn) : that.checkIn != null) return false;
        return checkOut != null ? checkOut.equals(that.checkOut) : that.checkOut == null;

    }

    @Override
    public int hashCode() {
        int result = checkIn != null ? checkIn.hashCode() : 0;
        result = 31 * result + (checkOut != null ? checkOut.hashCode() : 0);
        return result;
    }
}
