package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.CriteriaOwnerDTO;
import com.epam.apartmentbooking.entity.OwnerEntity;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public interface OwnerDao {

    public OwnerEntity findOne(int id);
    public List<Integer> returnOwnerIdByCriteria(CriteriaOwnerDTO criteriaOwnerEntity);
}
