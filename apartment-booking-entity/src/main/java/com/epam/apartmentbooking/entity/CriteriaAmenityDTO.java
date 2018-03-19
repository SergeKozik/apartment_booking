package com.epam.apartmentbooking.entity;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaAmenityDTO implements CriteriaDTOMarker, Serializable {

    private static final long serialVersionUID = 1L;

    private boolean wifiFlag;
    private boolean freeParkingFlag;

    public CriteriaAmenityDTO() {
    }

    public CriteriaAmenityDTO(boolean wifiFlag, boolean freeParkingFlag) {
        this.wifiFlag = wifiFlag;
        this.freeParkingFlag = freeParkingFlag;
    }

    public boolean isWifiFlag() {
        return wifiFlag;
    }

    public void setWifiFlag(boolean wifiFlag) {
        this.wifiFlag = wifiFlag;
    }

    public boolean isFreeParkingFlag() {
        return freeParkingFlag;
    }

    public void setFreeParkingFlag(boolean freeParkingFlag) {
        this.freeParkingFlag = freeParkingFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaAmenityDTO that = (CriteriaAmenityDTO) o;

        if (wifiFlag != that.wifiFlag) return false;
        return freeParkingFlag == that.freeParkingFlag;

    }

    @Override
    public int hashCode() {
        int result = (wifiFlag ? 1 : 0);
        result = 31 * result + (freeParkingFlag ? 1 : 0);
        return result;
    }
}
