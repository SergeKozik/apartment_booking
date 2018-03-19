package com.epam.apartmentbooking.entity;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class CriteriaOwnerDTO implements CriteriaDTOMarker, Serializable {

    private static final long serialVersionUID = 1L;

    private String titlePiece;
    private String addressPiece;

    public CriteriaOwnerDTO() {
    }

    public CriteriaOwnerDTO(String titlePiece, String addressPiece) {
        this.titlePiece = titlePiece;
        this.addressPiece = addressPiece;
    }

    public String getTitlePiece() {
        return titlePiece;
    }

    public void setTitlePiece(String titlePiece) {
        this.titlePiece = titlePiece;
    }

    public String getAddressPiece() {
        return addressPiece;
    }

    public void setAddressPiece(String addressPiece) {
        this.addressPiece = addressPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaOwnerDTO that = (CriteriaOwnerDTO) o;

        if (titlePiece != null ? !titlePiece.equals(that.titlePiece) : that.titlePiece != null) return false;
        return addressPiece != null ? addressPiece.equals(that.addressPiece) : that.addressPiece == null;

    }

    @Override
    public int hashCode() {
        int result = titlePiece != null ? titlePiece.hashCode() : 0;
        result = 31 * result + (addressPiece != null ? addressPiece.hashCode() : 0);
        return result;
    }
}
