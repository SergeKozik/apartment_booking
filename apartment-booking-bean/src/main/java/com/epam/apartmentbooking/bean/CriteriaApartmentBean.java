package com.epam.apartmentbooking.bean;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaApartmentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private CriteriaOwnerBean owner;
    private CriteriaSpaceBean space;
    private CriteriaAmenityBean amenity;
    private CriteriaAvailabilityBean availability;
    private CriteriaPriceBean price;
    private CriteriaTimeBookingBean timeBooking;

    public CriteriaApartmentBean() {
        this(Locale.ENGLISH);
    }

    public CriteriaApartmentBean(Locale locale) {
        this.owner = new CriteriaOwnerBean();
        this.space = new CriteriaSpaceBean();
        this.amenity = new CriteriaAmenityBean();
        this.availability = new CriteriaAvailabilityBean();
        this.price = new CriteriaPriceBean();
        this.timeBooking = new CriteriaTimeBookingBean(locale);
    }

    public CriteriaApartmentBean(CriteriaOwnerBean owner, CriteriaSpaceBean space, CriteriaAmenityBean amenity, CriteriaAvailabilityBean availability, CriteriaPriceBean price, CriteriaTimeBookingBean timeBooking) {
        this.owner = owner;
        this.space = space;
        this.amenity = amenity;
        this.availability = availability;
        this.price = price;
        this.timeBooking = timeBooking;
    }

    public CriteriaOwnerBean getOwner() {
        return owner;
    }

    public CriteriaSpaceBean getSpace() {
        return space;
    }

    public CriteriaAmenityBean getAmenity() {
        return amenity;
    }

    public CriteriaAvailabilityBean getAvailability() {
        return availability;
    }

    public CriteriaPriceBean getPrice() {
        return price;
    }

    public CriteriaTimeBookingBean getTimeBooking() {
        return timeBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaApartmentBean that = (CriteriaApartmentBean) o;

        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (space != null ? !space.equals(that.space) : that.space != null) return false;
        if (amenity != null ? !amenity.equals(that.amenity) : that.amenity != null) return false;
        if (availability != null ? !availability.equals(that.availability) : that.availability != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return timeBooking != null ? timeBooking.equals(that.timeBooking) : that.timeBooking == null;

    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        result = 31 * result + (space != null ? space.hashCode() : 0);
        result = 31 * result + (amenity != null ? amenity.hashCode() : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (timeBooking != null ? timeBooking.hashCode() : 0);
        return result;
    }
}
