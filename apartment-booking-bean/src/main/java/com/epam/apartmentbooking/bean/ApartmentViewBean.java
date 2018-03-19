package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/3/2017.
 */
public class ApartmentViewBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private OwnerViewBean owner;
    private SpaceViewBean space;
    private AmenityViewBean amenities;
    private AvailabilityViewBean availability;
    private PriceViewBean price;

    public ApartmentViewBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OwnerViewBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerViewBean owner) {
        this.owner = owner;
    }

    public SpaceViewBean getSpace() {
        return space;
    }

    public void setSpace(SpaceViewBean space) {
        this.space = space;
    }

    public AmenityViewBean getAmenities() {
        return amenities;
    }

    public void setAmenities(AmenityViewBean amenities) {
        this.amenities = amenities;
    }

    public AvailabilityViewBean getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityViewBean availability) {
        this.availability = availability;
    }

    public PriceViewBean getPrice() {
        return price;
    }

    public void setPrice(PriceViewBean price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApartmentViewBean that = (ApartmentViewBean) o;

        if (id != that.id) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (space != null ? !space.equals(that.space) : that.space != null) return false;
        if (amenities != null ? !amenities.equals(that.amenities) : that.amenities != null) return false;
        if (availability != null ? !availability.equals(that.availability) : that.availability != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (space != null ? space.hashCode() : 0);
        result = 31 * result + (amenities != null ? amenities.hashCode() : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
