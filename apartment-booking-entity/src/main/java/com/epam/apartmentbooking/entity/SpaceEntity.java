package com.epam.apartmentbooking.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */
@Entity
@Table(name = "T_SPACE")
public class SpaceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_APARTMENT_AP_ID")
    private int apartmentId;

    @Column(name = "SP_ACCOMMODATES")
    private int accommodates;

    @Column(name = "SP_BEDS")
    private int beds;

    @Column(name = "SP_ROOM_TYPE")
    private String roomType;


    public SpaceEntity() {
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getAccommodates() {
        return accommodates;
    }

    public void setAccommodates(int accommodates) {
        this.accommodates = accommodates;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpaceEntity that = (SpaceEntity) o;

        if (apartmentId != that.apartmentId) return false;
        if (accommodates != that.accommodates) return false;
        if (beds != that.beds) return false;
        return roomType != null ? roomType.equals(that.roomType) : that.roomType == null;

    }

    @Override
    public int hashCode() {
        int result = apartmentId;
        result = 31 * result + accommodates;
        result = 31 * result + beds;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        return result;
    }
}
