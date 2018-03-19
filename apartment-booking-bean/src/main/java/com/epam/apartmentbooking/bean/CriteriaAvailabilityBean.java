package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaAvailabilityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int minNightsMin;
    private int minNightsMax;

    public CriteriaAvailabilityBean() {
        this.minNightsMax=0;
        this.minNightsMax=0;
    }

    public CriteriaAvailabilityBean(int minNightsMin, int minNightsMax) {
        this.minNightsMin = minNightsMin;
        this.minNightsMax = minNightsMax;
    }

    public int getMinNightsMin() {
        return minNightsMin;
    }

    public void setMinNightsMin(int minNightsMin) {
        this.minNightsMin = minNightsMin;
    }

    public int getMinNightsMax() {
        return minNightsMax;
    }

    public void setMinNightsMax(int minNightsMax) {
        this.minNightsMax = minNightsMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaAvailabilityBean that = (CriteriaAvailabilityBean) o;

        if (minNightsMin != that.minNightsMin) return false;
        return minNightsMax == that.minNightsMax;

    }

    @Override
    public int hashCode() {
        int result = minNightsMin;
        result = 31 * result + minNightsMax;
        return result;
    }
}

