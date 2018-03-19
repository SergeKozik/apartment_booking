package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.ApartmentEntity;
import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */
public interface ApartmentDao {
    public List<ApartmentEntity> showByAddress(String place);
    public List<Integer> returnIdByOwnerId(List<Integer> ownerIds);
    public List<ApartmentEntity> findById(List<Integer> ids);
    public ApartmentEntity findOne(int id);
    public String showOwnerNameByAptId(int id);
    public List<ApartmentEntity> showAll();
    public List<Integer> showAllIds();
    public List<Integer> returnIdByCriteria(CriteriaApartmentDTO criteriaApartmentDTO);
}
