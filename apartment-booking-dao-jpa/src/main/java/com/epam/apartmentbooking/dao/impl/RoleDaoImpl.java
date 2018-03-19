package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.RoleDao;
import com.epam.apartmentbooking.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by Serge_Kozik on 6/14/2017.
 */

@Repository
public class RoleDaoImpl implements RoleDao {

    private final static String JPQL_SELECT_BY_NAME = "SELECT r FROM RoleEntity r WHERE r.name = :rolename";

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl() {
    }

    @Override
    public RoleEntity findOne(int id) {
        return entityManager.find(RoleEntity.class, id);
    }

    @Override
    public RoleEntity findByName(String name) {
        TypedQuery<RoleEntity> query = entityManager.createQuery(JPQL_SELECT_BY_NAME,RoleEntity.class);
        query.setParameter("rolename",name);
        return query.getSingleResult();
    }
}
