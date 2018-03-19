package com.epam.apartmentbooking.bean;

import com.epam.apartmentbooking.bean.validation.BedsConstraint;
import com.epam.apartmentbooking.bean.validation.GuestsConstraint;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaSpaceBean implements Serializable{

    private static final long serialVersionUID = 1L;

    @GuestsConstraint
    private int accomodatesMax;

    @GuestsConstraint
    private int accomodatesMin;

    @BedsConstraint
    private int bedsMax;

    @BedsConstraint
    private int bedsMin;

    private List<String> roomTypePiece;

    public CriteriaSpaceBean()
    {
        roomTypePiece = new ArrayList<>();
        accomodatesMax = 0;
        accomodatesMin = 0;
        bedsMax = 0;
        bedsMin = 0;
    }

    public CriteriaSpaceBean(int accomodatesMax, int accomodatesMin, int bedsMax, int bedsMin, List<String> roomTypePiece) {
        this();
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

        CriteriaSpaceBean that = (CriteriaSpaceBean) o;

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
