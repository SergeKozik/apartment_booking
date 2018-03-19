package com.epam.apartmentbooking.dao.impl.daofilter;

import com.epam.apartmentbooking.dao.AvailabilityDao;
import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;
import com.epam.apartmentbooking.entity.CriteriaAvailabilityDTO;

import java.util.List;

/**
 * Created by Roldo on 05.07.2017.
 */
public class AvailabilityFilter implements DaoFilterMarker {

    private AvailabilityDao availabilityDao;

    public AvailabilityFilter(AvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
    }

    @Override
    public List<Integer> filterApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaAvailabilityDTO criteriaAvailabilityDTO = criteriaApartmentDTO.getAvailability();
        if (criteriaAvailabilityDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = availabilityDao.returnApartmentIdByCriteria(criteriaAvailabilityDTO);
        if (filteredIds!=null) {
            apartmentIds.retainAll(filteredIds);
        }
        return apartmentIds;
    }

    @Override
    public List<Integer> addApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaAvailabilityDTO criteriaAvailabilityDTO = criteriaApartmentDTO.getAvailability();
        if (criteriaAvailabilityDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = availabilityDao.returnApartmentIdByCriteria(criteriaAvailabilityDTO);
        if ((filteredIds!=null)&&(!filteredIds.isEmpty())) {
            apartmentIds.addAll(filteredIds);
        }
        return apartmentIds;
    }
}
