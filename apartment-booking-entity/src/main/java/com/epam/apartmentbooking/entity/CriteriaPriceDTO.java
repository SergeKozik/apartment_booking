package com.epam.apartmentbooking.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaPriceDTO implements CriteriaDTOMarker, Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal dailyMin;
    private BigDecimal dailyMax;
    private double weeklyDiscountMin;
    private double weeklyDiscountMax;
    private double monthlyDiscountMin;
    private double monthlyDiscountMax;

    public CriteriaPriceDTO() {
    }

    public CriteriaPriceDTO(BigDecimal dailyMin, BigDecimal dailyMax, double weeklyDiscountMin, double weeklyDiscountMax, double monthlyDiscountMin, double monthlyDiscountMax) {
        this.dailyMin = dailyMin;
        this.dailyMax = dailyMax;
        this.weeklyDiscountMin = weeklyDiscountMin;
        this.weeklyDiscountMax = weeklyDiscountMax;
        this.monthlyDiscountMin = monthlyDiscountMin;
        this.monthlyDiscountMax = monthlyDiscountMax;
    }

    public BigDecimal getDailyMin() {
        return dailyMin;
    }

    public void setDailyMin(BigDecimal dailyMin) {
        this.dailyMin = dailyMin;
    }

    public BigDecimal getDailyMax() {
        return dailyMax;
    }

    public void setDailyMax(BigDecimal dailyMax) {
        this.dailyMax = dailyMax;
    }

    public double getWeeklyDiscountMin() {
        return weeklyDiscountMin;
    }

    public void setWeeklyDiscountMin(double weeklyDiscountMin) {
        this.weeklyDiscountMin = weeklyDiscountMin;
    }

    public double getWeeklyDiscountMax() {
        return weeklyDiscountMax;
    }

    public void setWeeklyDiscountMax(double weeklyDiscountMax) {
        this.weeklyDiscountMax = weeklyDiscountMax;
    }

    public double getMonthlyDiscountMin() {
        return monthlyDiscountMin;
    }

    public void setMonthlyDiscountMin(double monthlyDiscountMin) {
        this.monthlyDiscountMin = monthlyDiscountMin;
    }

    public double getMonthlyDiscountMax() {
        return monthlyDiscountMax;
    }

    public void setMonthlyDiscountMax(double monthlyDiscountMax) {
        this.monthlyDiscountMax = monthlyDiscountMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaPriceDTO that = (CriteriaPriceDTO) o;

        if (Double.compare(that.weeklyDiscountMin, weeklyDiscountMin) != 0) return false;
        if (Double.compare(that.weeklyDiscountMax, weeklyDiscountMax) != 0) return false;
        if (Double.compare(that.monthlyDiscountMin, monthlyDiscountMin) != 0) return false;
        if (Double.compare(that.monthlyDiscountMax, monthlyDiscountMax) != 0) return false;
        if (dailyMin != null ? !dailyMin.equals(that.dailyMin) : that.dailyMin != null) return false;
        return dailyMax != null ? dailyMax.equals(that.dailyMax) : that.dailyMax == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dailyMin != null ? dailyMin.hashCode() : 0;
        result = 31 * result + (dailyMax != null ? dailyMax.hashCode() : 0);
        temp = Double.doubleToLongBits(weeklyDiscountMin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weeklyDiscountMax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(monthlyDiscountMin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(monthlyDiscountMax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
