package com.epam.apartmentbooking.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */
@Entity
@Table(name = "T_PRICES")
public class PriceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_APARTMENT_AP_ID")
    private int apartmentId;

    @Column(name = "PR_DAILY")
    private BigDecimal daily;

    @Column(name = "PR_WEEKLY_DISCOUNT")
    private double weeklyDiscount;

    @Column(name = "PR_MONTHLY_DISCOUNT")
    private double monthlyDiscount;


    public PriceEntity() {
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

        PriceEntity that = (PriceEntity) o;

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
