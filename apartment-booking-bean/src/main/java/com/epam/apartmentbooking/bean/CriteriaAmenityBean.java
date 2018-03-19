package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaAmenityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean wifiFlag;
    private boolean freeParkingFlag;

    public CriteriaAmenityBean() {
        this.wifiFlag = false;
        this.freeParkingFlag = false;
    }

    public CriteriaAmenityBean(boolean wifiFlag, boolean freeParkingFlag) {
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

        CriteriaAmenityBean that = (CriteriaAmenityBean) o;

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
