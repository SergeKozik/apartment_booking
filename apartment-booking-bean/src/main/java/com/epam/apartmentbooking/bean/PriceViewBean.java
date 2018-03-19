package com.epam.apartmentbooking.bean;

import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Serge_Kozik on 4/17/2017.
 */
public class PriceViewBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int apartmentId;
    private BigDecimal daily;
    private double weeklyDiscount;
    private double monthlyDiscount;

    public PriceViewBean() {
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public BigDecimal getDaily() {
        return daily;
    }

    public void setDaily(BigDecimal daily) {
        this.daily = daily;
    }

    public double getWeeklyDiscount() {
        return weeklyDiscount;
    }

    public void setWeeklyDiscount(double weeklyDiscount) {
        this.weeklyDiscount = weeklyDiscount;
    }

    public double getMonthlyDiscount() {
        return monthlyDiscount;
    }

    public void setMonthlyDiscount(double monthlyDiscount) {
        this.monthlyDiscount = monthlyDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceViewBean that = (PriceViewBean) o;

        if (apartmentId != that.apartmentId) return false;
        if (Double.compare(that.weeklyDiscount, weeklyDiscount) != 0) return false;
        if (Double.compare(that.monthlyDiscount, monthlyDiscount) != 0) return false;
        return daily != null ? daily.equals(that.daily) : that.daily == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = apartmentId;
        result = 31 * result + (daily != null ? daily.hashCode() : 0);
        temp = Double.doubleToLongBits(weeklyDiscount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(monthlyDiscount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
