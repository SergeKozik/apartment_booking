package com.epam.apartmentbooking.service.dozer.impl;

import com.epam.apartmentbooking.dao.RoleDao;
import com.epam.apartmentbooking.entity.RoleEntity;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Serge_Kozik on 6/14/2017.
 */
public class RoleToStringConverter extends DozerConverter<RoleEntity,String> {

    @Autowired
    private RoleDao roleDao;

    public RoleToStringConverter() {
        super(RoleEntity.class, String.class);
    }

    @Override
    public String convertTo(RoleEntity roleEntity, String s) {
        if (roleEntity==null) {
            return null;
        }
        return roleEntity.getName();
    }

    @Override
    public RoleEntity convertFrom(String s, RoleEntity roleEntity) {
        if ((s==null)||s.isEmpty()) {
            return null;
        }
        return roleDao.findByName(s);
    }
}
