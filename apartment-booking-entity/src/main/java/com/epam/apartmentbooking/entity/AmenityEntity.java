package com.epam.apartmentbooking.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */
@Entity
@Table(name = "T_AMENITIES")
public class AmenityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_APARTMENT_AP_ID")
    private int apartmentId;

    @Column(name = "AM_WIRELESS")
    private boolean wifi;

    @Column(name = "AM_FREE_PARKING")
    private boolean freeParking;

    public AmenityEntity() {
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

        AmenityEntity that = (AmenityEntity) o;

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
