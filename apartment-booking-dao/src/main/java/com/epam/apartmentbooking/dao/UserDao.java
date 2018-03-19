package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.UserEntity;

/**
 * Created by Serge_Kozik on 4/5/2017.
 */
public interface UserDao {

    public UserEntity findOne(int id);
    public UserEntity findByEmail(String email);
    public UserEntity findByEmailAndPassword(String email, String password);
    public UserEntity findByNickAndPassword(String nick, String password);
    public UserEntity save(UserEntity entity);
}
