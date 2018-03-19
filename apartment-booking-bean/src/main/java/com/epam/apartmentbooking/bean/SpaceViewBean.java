package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/17/2017.
 */
public class SpaceViewBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int apartmentId;
    private int accommodates;
    private int beds;
    private RoomTypeEnum roomType;

    public SpaceViewBean() {
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

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpaceViewBean that = (SpaceViewBean) o;

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
