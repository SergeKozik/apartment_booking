package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.UserDao;
import com.epam.apartmentbooking.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 * Created by Serge_Kozik on 4/5/2017.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final String JPQL_SELECT_BY_EMAIL="SELECT u FROM UserEntity u WHERE u.email=:emailfield";
    private static final String JPQL_SELECT_BY_EMAIL_PASSW="SELECT u FROM UserEntity u WHERE u.email=:emailfield AND u.password=:passwfield";
    private static final String JPQL_SELECT_BY_NAME_PASSW="SELECT u FROM UserEntity u WHERE u.nick=:nickfield AND u.password=:passwfield";

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
    }

    @Override
    public UserEntity findOne(int id) {
        return entityManager.find(UserEntity.class,id);
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public UserEntity findByEmail(String email) {

        TypedQuery<UserEntity> query = entityManager.createQuery(JPQL_SELECT_BY_EMAIL,UserEntity.class);
        query.setParameter("emailfield",email);
        return query.getSingleResult();
    }

    @Override
    public UserEntity findByEmailAndPassword(String email, String password) {
        TypedQuery<UserEntity> query = entityManager.createQuery(JPQL_SELECT_BY_EMAIL_PASSW,UserEntity.class);
        query.setParameter("emailfield",email);
        query.setParameter("passwfield",password);
        return query.getSingleResult();
    }

    @Override
    public UserEntity findByNickAndPassword(String nick, String password) {
        TypedQuery<UserEntity> query = entityManager.createQuery(JPQL_SELECT_BY_NAME_PASSW,UserEntity.class);
        query.setParameter("nickfield",nick);
        query.setParameter("passwfield",password);
        return query.getSingleResult();
    }
}
