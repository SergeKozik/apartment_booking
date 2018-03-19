package com.epam.apartmentbooking.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Serge_Kozik on 5/15/2017.
 */
public class BookingViewBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String apartmentName;
    private String checkIn;
    private String checkOut;
    private int guests;
    private BigDecimal price;

    public BookingViewBean() {
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingViewBean that = (BookingViewBean) o;

        if (guests != that.guests) return false;
        if (apartmentName != null ? !apartmentName.equals(that.apartmentName) : that.apartmentName != null)
            return false;
        if (checkIn != null ? !checkIn.equals(that.checkIn) : that.checkIn != null) return false;
        if (checkOut != null ? !checkOut.equals(that.checkOut) : that.checkOut != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;

    }

    @Override
    public int hashCode() {
        int result = apartmentName != null ? apartmentName.hashCode() : 0;
        result = 31 * result + (checkIn != null ? checkIn.hashCode() : 0);
        result = 31 * result + (checkOut != null ? checkOut.hashCode() : 0);
        result = 31 * result + guests;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
