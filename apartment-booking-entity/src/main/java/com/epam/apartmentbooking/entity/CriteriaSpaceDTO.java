package com.epam.apartmentbooking.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaSpaceDTO implements CriteriaDTOMarker, Serializable{

    private static final long serialVersionUID = 1L;

    private int accomodatesMax;
    private int accomodatesMin;
    private int bedsMax;
    private int bedsMin;
    private List<String> roomTypePiece;

    public CriteriaSpaceDTO() {
        roomTypePiece = new ArrayList<>();
    }

    public CriteriaSpaceDTO(int accomodatesMax, int accomodatesMin, int bedsMax, int bedsMin, List<String> roomTypePiece) {
        this.accomodatesMax = accomodatesMax;
        this.accomodatesMin = accomodatesMin;
        this.bedsMax = bedsMax;
        this.bedsMin = bedsMin;
        this.roomTypePiece = roomTypePiece;
    }

    public int getAccomodatesMax() {
        return accomodatesMax;
    }

    public void setAccomodatesMax(int accomodatesMax) {
        this.accomodatesMax = accomodatesMax;
    }

    public int getAccomodatesMin() {
        return accomodatesMin;
    }

    public void setAccomodatesMin(int accomodatesMin) {
        this.accomodatesMin = accomodatesMin;
    }

    public int getBedsMax() {
        return bedsMax;
    }

    public void setBedsMax(int bedsMax) {
        this.bedsMax = bedsMax;
    }

    public int getBedsMin() {
        return bedsMin;
    }

    public void setBedsMin(int bedsMin) {
        this.bedsMin = bedsMin;
    }

    public List<String> getRoomTypePiece() {
        return roomTypePiece;
    }

    public void setRoomTypePiece(List<String> roomTypePiece) {
        this.roomTypePiece = roomTypePiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaSpaceDTO that = (CriteriaSpaceDTO) o;

        if (accomodatesMax != that.accomodatesMax) return false;
        if (accomodatesMin != that.accomodatesMin) return false;
        if (bedsMax != that.bedsMax) return false;
        if (bedsMin != that.bedsMin) return false;
        return roomTypePiece != null ? roomTypePiece.equals(that.roomTypePiece) : that.roomTypePiece == null;

    }

    @Override
    public int hashCode() {
        int result = accomodatesMax;
        result = 31 * result + accomodatesMin;
        result = 31 * result + bedsMax;
        result = 31 * result + bedsMin;
        result = 31 * result + (roomTypePiece != null ? roomTypePiece.hashCode() : 0);
        return result;
    }
}
