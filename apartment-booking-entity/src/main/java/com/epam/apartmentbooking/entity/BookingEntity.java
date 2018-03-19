package com.epam.apartmentbooking.entity;

import com.epam.apartmentbooking.entity.jpaconverter.MoneyToDecimal;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */

@Entity
@Table(name = "T_BOOKING")
public class BookingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BO_ID")
    private int id;

    @Column(name = "BO_CHECK_IN")
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Column(name = "BO_CHECK_OUT")
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @Column(name = "BO_TOTAL_SUM")
    @Convert(converter = MoneyToDecimal.class)
    private BigDecimal totalSum;

    @Column(name = "BO_DATE_BOOKED")
    @Temporal(value = TemporalType.DATE)
    private Date dateBooked;

    @Column(name = "T_APARTMENT_AP_ID")
    private int apartmentId;

    @Column(name = "T_USER_US_ID")
    private int userId;

    @Column(name = "BO_GUESTS")
    private int guests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_APARTMENT_AP_ID", referencedColumnName = "AP_ID", insertable = false, updatable = false)
    private ApartmentEntity apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_USER_US_ID", referencedColumnName = "US_ID", insertable = false, updatable = false)
    private UserEntity user;

    public BookingEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public Date getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(Date dateBooked) {
        this.dateBooked = dateBooked;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public ApartmentEntity getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentEntity apartment) {
        this.apartment = apartment;
        this.apartmentId = apartment.getId();
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        this.userId = user.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingEntity that = (BookingEntity) o;

        if (id != that.id) return false;
        if (apartmentId != that.apartmentId) return false;
        if (userId != that.userId) return false;
        if (guests != that.guests) return false;
        if (checkIn != null ? !checkIn.equals(that.checkIn) : that.checkIn != null) return false;
        if (checkOut != null ? !checkOut.equals(that.checkOut) : that.checkOut != null) return false;
        if (totalSum != null ? !totalSum.equals(that.totalSum) : that.totalSum != null) return false;
        if (dateBooked != null ? !dateBooked.equals(that.dateBooked) : that.dateBooked != null) return false;
        if (apartment != null ? !apartment.equals(that.apartment) : that.apartment != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (checkIn != null ? checkIn.hashCode() : 0);
        result = 31 * result + (checkOut != null ? checkOut.hashCode() : 0);
        result = 31 * result + (totalSum != null ? totalSum.hashCode() : 0);
        result = 31 * result + (dateBooked != null ? dateBooked.hashCode() : 0);
        result = 31 * result + apartmentId;
        result = 31 * result + userId;
        result = 31 * result + guests;
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
