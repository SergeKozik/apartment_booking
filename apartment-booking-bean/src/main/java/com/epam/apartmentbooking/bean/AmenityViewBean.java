package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/17/2017.
 */
public class AmenityViewBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int apartmentId;
    private boolean wifi;
    private boolean freeParking;

    public AmenityViewBean() {
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isFreeParking() {
        return freeParking;
    }

    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmenityViewBean that = (AmenityViewBean) o;

        if (apartmentId != that.apartmentId) return false;
        if (wifi != that.wifi) return false;
        return freeParking == that.freeParking;

    }

    @Override
    public int hashCode() {
        int result = apartmentId;
        result = 31 * result + (wifi ? 1 : 0);
        result = 31 * result + (freeParking ? 1 : 0);
        return result;
    }
}
