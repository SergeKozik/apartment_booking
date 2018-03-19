package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.CriteriaSpaceDTO;
import com.epam.apartmentbooking.entity.SpaceEntity;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public interface SpaceDao {
    public SpaceEntity findByApartment_Id(int apartmentId);
    public List<Integer> returnApartmentIdByCriteria(CriteriaSpaceDTO criteriaSpaceEntity);
}
