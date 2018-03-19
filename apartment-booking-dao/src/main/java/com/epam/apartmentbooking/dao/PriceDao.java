package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.CriteriaPriceDTO;
import com.epam.apartmentbooking.entity.PriceEntity;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public interface PriceDao {
    public PriceEntity findByApartment_Id(int apartmentId);
    public List<Integer> returnApartmentIdByCriteria(CriteriaPriceDTO criteriaPriceEntity);
}
