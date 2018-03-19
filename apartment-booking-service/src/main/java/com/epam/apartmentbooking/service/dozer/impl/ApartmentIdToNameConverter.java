package com.epam.apartmentbooking.service.dozer.impl;

import com.epam.apartmentbooking.dao.ApartmentDao;
import com.epam.apartmentbooking.entity.ApartmentEntity;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Serge_Kozik on 5/15/2017.
 */
public class ApartmentIdToNameConverter extends DozerConverter<Integer, String> {

    @Autowired
    private ApartmentDao apartmentDao;

    public ApartmentIdToNameConverter() {
        super(Integer.class, String.class);
    }

    @Override
    public String convertTo(Integer integer, String s) {
        return apartmentDao.showOwnerNameByAptId(integer);
    }

    @Override
    public Integer convertFrom(String s, Integer integer) {
        return null;
    }
}
