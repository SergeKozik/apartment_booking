package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.AmenityEntity;
import com.epam.apartmentbooking.entity.CriteriaAmenityDTO;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public interface AmenityDao {
    public AmenityEntity findByApartment_Id(int apartmentId);
    public List<Integer> returnApartmentIdByCriteria(CriteriaAmenityDTO criteriaAmenityDTO);
}
