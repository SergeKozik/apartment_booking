package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.AvailabilityEntity;
import com.epam.apartmentbooking.entity.CriteriaAvailabilityDTO;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public interface AvailabilityDao {

    public AvailabilityEntity findByApartment_Id(int apartmentId);
    public List<Integer> returnApartmentIdByCriteria(CriteriaAvailabilityDTO criteriaAvailabilityDTO);
}
