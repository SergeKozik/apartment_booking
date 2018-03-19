package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.bean.ApartmentViewBean;
import com.epam.apartmentbooking.bean.CriteriaApartmentBean;
import com.epam.apartmentbooking.entity.ApartmentEntity;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/3/2017.
 */
public interface ApartmentService {
    public List<ApartmentViewBean> filterAvailable(CriteriaApartmentBean criteria);
    public List<ApartmentViewBean> showAll();
    public ApartmentViewBean showOne(int id);
}
