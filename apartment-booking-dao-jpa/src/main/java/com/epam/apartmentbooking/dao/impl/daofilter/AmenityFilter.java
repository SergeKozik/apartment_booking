package com.epam.apartmentbooking.dao.impl.daofilter;

import com.epam.apartmentbooking.dao.AmenityDao;
import com.epam.apartmentbooking.entity.CriteriaAmenityDTO;
import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;

import java.util.List;

/**
 * Created by Roldo on 05.07.2017.
 */
public class AmenityFilter implements DaoFilterMarker {

    private AmenityDao amenityDao;

    public AmenityFilter(AmenityDao amenityDao) {
        this.amenityDao = amenityDao;
    }

    @Override
    public List<Integer> filterApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaAmenityDTO criteriaAmenityDTO = criteriaApartmentDTO.getAmenity();
        if (criteriaAmenityDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = amenityDao.returnApartmentIdByCriteria(criteriaAmenityDTO);
        if (filteredIds!=null) {
            apartmentIds.retainAll(filteredIds);
        }
        return apartmentIds;
    }

    @Override
    public List<Integer> addApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaAmenityDTO criteriaAmenityDTO = criteriaApartmentDTO.getAmenity();
        if (criteriaAmenityDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = amenityDao.returnApartmentIdByCriteria(criteriaAmenityDTO);
        if ((filteredIds!=null)&&(!filteredIds.isEmpty())) {
            apartmentIds.addAll(filteredIds);
        }
        return apartmentIds;
    }
}
