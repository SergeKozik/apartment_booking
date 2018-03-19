package com.epam.apartmentbooking.dao.impl.daofilter;

import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;

import java.util.List;

/**
 * Created by Roldo on 05.07.2017.
 */
public interface DaoFilterMarker {

    public List<Integer> filterApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO);
    public List<Integer> addApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO);
}
