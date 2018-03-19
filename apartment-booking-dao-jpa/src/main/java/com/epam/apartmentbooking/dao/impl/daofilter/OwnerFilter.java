package com.epam.apartmentbooking.dao.impl.daofilter;

import com.epam.apartmentbooking.dao.ApartmentDao;
import com.epam.apartmentbooking.dao.OwnerDao;
import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;
import com.epam.apartmentbooking.entity.CriteriaOwnerDTO;

import java.util.List;

/**
 * Created by Roldo on 05.07.2017.
 */
public class OwnerFilter implements DaoFilterMarker {

    private OwnerDao ownerDao;
    private ApartmentDao apartmentDao;

    public OwnerFilter(OwnerDao ownerDao, ApartmentDao apartmentDao) {
        this.ownerDao = ownerDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    public List<Integer> filterApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaOwnerDTO criteriaOwnerEntity = criteriaApartmentDTO.getOwner();
        if (criteriaOwnerEntity==null) {
            return apartmentIds;
        }
        List<Integer> ownerIds = ownerDao.returnOwnerIdByCriteria(criteriaOwnerEntity);
        List<Integer> filteredIds = apartmentDao.returnIdByOwnerId(ownerIds);
        if (filteredIds!=null) {
            apartmentIds.retainAll(filteredIds);
        }
        return apartmentIds;
    }

    @Override
    public List<Integer> addApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaOwnerDTO criteriaOwnerEntity = criteriaApartmentDTO.getOwner();
        if (criteriaOwnerEntity==null) {
            return apartmentIds;
        }
        List<Integer> ownerIds = ownerDao.returnOwnerIdByCriteria(criteriaOwnerEntity);
        List<Integer> filteredIds = apartmentDao.returnIdByOwnerId(ownerIds);
        if ((filteredIds!=null)&&(!filteredIds.isEmpty())) {
            apartmentIds.addAll(filteredIds);
        }
        return apartmentIds;
    }
}
