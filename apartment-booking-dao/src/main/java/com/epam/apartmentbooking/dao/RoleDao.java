package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.RoleEntity;

/**
 * Created by Serge_Kozik on 6/14/2017.
 */
public interface RoleDao {
    public RoleEntity findOne(int id);
    public RoleEntity findByName(String name);
}
