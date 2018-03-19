package com.epam.apartmentbooking.dao.impl.daofilter;

import com.epam.apartmentbooking.dao.SpaceDao;
import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;
import com.epam.apartmentbooking.entity.CriteriaSpaceDTO;

import java.util.List;

/**
 * Created by Roldo on 05.07.2017.
 */
public class SpaceFilter implements DaoFilterMarker {

    private SpaceDao spaceDao;

    public SpaceFilter(SpaceDao spaceDao) {
        this.spaceDao = spaceDao;
    }

    @Override
    public List<Integer> filterApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaSpaceDTO criteriaSpaceDTO = criteriaApartmentDTO.getSpace();
        if (criteriaSpaceDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = spaceDao.returnApartmentIdByCriteria(criteriaSpaceDTO);
        if (filteredIds!=null) {
            apartmentIds.retainAll(filteredIds);
        }
        return apartmentIds;
    }

    @Override
    public List<Integer> addApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaSpaceDTO criteriaSpaceDTO = criteriaApartmentDTO.getSpace();
        if (criteriaSpaceDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = spaceDao.returnApartmentIdByCriteria(criteriaSpaceDTO);
        if ((filteredIds!=null)&&(!filteredIds.isEmpty())) {
            apartmentIds.addAll(filteredIds);
        }
        return apartmentIds;
    }
}
