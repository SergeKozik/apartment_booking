package com.epam.apartmentbooking.entity;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaAvailabilityDTO implements CriteriaDTOMarker, Serializable {

    private static final long serialVersionUID = 1L;

    private int minNightsMin;
    private int minNightsMax;

    public CriteriaAvailabilityDTO() {
    }

    public CriteriaAvailabilityDTO(int minNightsMin, int minNightsMax) {
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

        CriteriaAvailabilityDTO that = (CriteriaAvailabilityDTO) o;

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

