package com.epam.apartmentbooking.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */

@Entity
@Table(name = "T_AVAILABILITY")
public class AvailabilityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_APARTMENT_AP_ID")
    private int apartmentId;

    @Column(name = "AV_MIN_NIGHT")
    private int minNights;


    public AvailabilityEntity() {
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

        AvailabilityEntity that = (AvailabilityEntity) o;

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
