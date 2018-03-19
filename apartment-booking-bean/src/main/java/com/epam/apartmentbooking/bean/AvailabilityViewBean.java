package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/17/2017.
 */
public class AvailabilityViewBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int apartmentId;
    private int minNights;

    public AvailabilityViewBean() {
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getMinNights() {
        return minNights;
    }

    public void setMinNights(int minNights) {
        this.minNights = minNights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailabilityViewBean that = (AvailabilityViewBean) o;

        if (apartmentId != that.apartmentId) return false;
        return minNights == that.minNights;

    }

    @Override
    public int hashCode() {
        int result = apartmentId;
        result = 31 * result + minNights;
        return result;
    }
}
