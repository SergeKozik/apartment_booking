package com.epam.apartmentbooking.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */
@Entity
@Table(name = "T_APARTMENT")
public class ApartmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AP_ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_OWNER_OW_ID", referencedColumnName = "OW_ID")
    private OwnerEntity owner;

    @OneToOne
    @PrimaryKeyJoinColumn
    private SpaceEntity space;

    @OneToOne
    @PrimaryKeyJoinColumn
    private AmenityEntity amenities;

    @OneToOne
    @PrimaryKeyJoinColumn
    private AvailabilityEntity availability;

    @OneToOne
    @PrimaryKeyJoinColumn
    private PriceEntity price;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "apartment")
    private List<BookingEntity> bookings;

    public ApartmentEntity() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public SpaceEntity getSpace() {
        return space;
    }

    public void setSpace(SpaceEntity space) {
        this.space = space;
    }

    public AmenityEntity getAmenities() {
        return amenities;
    }

    public void setAmenities(AmenityEntity amenities) {
        this.amenities = amenities;
    }

    public AvailabilityEntity getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityEntity availability) {
        this.availability = availability;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApartmentEntity that = (ApartmentEntity) o;

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
