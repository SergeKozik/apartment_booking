package com.epam.apartmentbooking.entity;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaApartmentDTO implements CriteriaDTOMarker, Serializable {
    private static final long serialVersionUID = 1L;

    private CriteriaOwnerDTO owner;
    private CriteriaSpaceDTO space;
    private CriteriaAmenityDTO amenity;
    private CriteriaAvailabilityDTO availability;
    private CriteriaPriceDTO price;
    private CriteriaTimeBookingDTO timeBooking;

    public CriteriaApartmentDTO() {
    }

    public CriteriaApartmentDTO(CriteriaOwnerDTO owner, CriteriaSpaceDTO space, CriteriaAmenityDTO amenity, CriteriaAvailabilityDTO availability, CriteriaPriceDTO price, CriteriaTimeBookingDTO timeBooking) {
        this.owner = owner;
        this.space = space;
        this.amenity = amenity;
        this.availability = availability;
        this.price = price;
        this.timeBooking = timeBooking;
    }

    public CriteriaOwnerDTO getOwner() {
        return owner;
    }

    public CriteriaSpaceDTO getSpace() {
        return space;
    }

    public CriteriaAmenityDTO getAmenity() {
        return amenity;
    }

    public CriteriaAvailabilityDTO getAvailability() {
        return availability;
    }

    public CriteriaPriceDTO getPrice() {
        return price;
    }

    public CriteriaTimeBookingDTO getTimeBooking() {
        return timeBooking;
    }

    public void setOwner(CriteriaOwnerDTO owner) {
        this.owner = owner;
    }

    public void setSpace(CriteriaSpaceDTO space) {
        this.space = space;
    }

    public void setAmenity(CriteriaAmenityDTO amenity) {
        this.amenity = amenity;
    }

    public void setAvailability(CriteriaAvailabilityDTO availability) {
        this.availability = availability;
    }

    public void setPrice(CriteriaPriceDTO price) {
        this.price = price;
    }

    public void setTimeBooking(CriteriaTimeBookingDTO timeBooking) {
        this.timeBooking = timeBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaApartmentDTO that = (CriteriaApartmentDTO) o;

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
