package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.bean.*;
import com.epam.apartmentbooking.dao.UserDao;
import com.epam.apartmentbooking.entity.UserEntity;
import com.epam.apartmentbooking.service.dozer.EntityBeanConverter;
import com.epam.apartmentbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Serge_Kozik on 4/6/2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private EntityBeanConverter converter;

    @Autowired
    public UserServiceImpl(UserDao userDao, EntityBeanConverter converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public UserProfileBean registerUser(UserRegisterBean newUser) {
        UserEntity existedUser = userDao.findByEmail(newUser.getEmail());
        if (existedUser!=null) {
            return null;
        }
        UserEntity userEntity = converter.convertToEntity(newUser,UserEntity.class);
        userEntity.setPassword(codePassword(newUser.getPassword()));
        UserEntity savedEntity = userDao.save(userEntity);
        if (savedEntity==null) {
            return null;
        }
        UserProfileBean result = converter.convertToBean(savedEntity,UserProfileBean.class);
        return result;
    }

    @Override
    public UserProfileBean findOne(int userId) {
        UserEntity userEntity = userDao.findOne(userId);
        if (userEntity==null) {
            return null;
        }
        UserProfileBean result = converter.convertToBean(userEntity,UserProfileBean.class);
        return result;
    }

    @Override
    public UserProfileBean findByEmail(String email) {
        UserEntity userEntity = userDao.findByEmail(email);
        if (userEntity==null) {
            return null;
        }
        UserProfileBean result = converter.convertToBean(userEntity,UserProfileBean.class);
        return result;
    }

    @Override
    public UserProfileBean loginUser(UserLoginBean loginBean) {
        UserEntity userEntity = null;
        userEntity = userDao.findByEmailAndPassword(loginBean.getEmail(), loginBean.getPassword());
        if (userEntity==null) {
            return null;
        }
        UserProfileBean result = converter.convertToBean(userEntity,UserProfileBean.class);
        return result;
    }


    @Override
    public PasswordChangingBean restorePassword(String email) {
        UserEntity userEntity = userDao.findByEmail(email);
        if (userEntity==null) {
            return null;
        }
        String newPassword = generatePassword();
        PasswordChangingBean result = new PasswordChangingBean();
        result.setNewPassword(newPassword);
        result.setNewPasswordConfirm(newPassword);
        result.setOldPassword(newPassword);
        userEntity.setPassword(newPassword);
        UserEntity savedEntity = userDao.save(userEntity);
        if (savedEntity==null) {
            return null;
        }
        return result;
    }

    @Override
    public UserProfileBean editProfile(UserProfileBean profileBean, UserProfileChangeBean changeBean) {
        if ((profileBean==null)||(changeBean==null)) {
            return null;
        }
        UserEntity currentEntity = userDao.findOne(profileBean.getId());
        if (comparePasswords(changeBean.getOldPassword(),currentEntity.getPassword())) {
            String nick = changeBean.getNick();
            String newPassword = changeBean.getPassword();
            if ((nick!=null)&&(!nick.isEmpty())) {
                currentEntity.setNick(nick);
            }
            if ((newPassword!=null)&&(!newPassword.isEmpty())) {
                currentEntity.setPassword(codePassword(newPassword));
            }
            UserEntity newEntity = userDao.save(currentEntity);
            if (newEntity==null) {
                return null;
            }
            UserProfileBean newProfile = converter.convertToBean(newEntity,UserProfileBean.class);
            return newProfile;
        } else {
            return null;
        }
    }

    private String generatePassword() {
        return "123";
    }

    @Override
    public String codePassword(String nonCoded) {
        return nonCoded;
    }

    private boolean comparePasswords(String nonCoded, String coded) {
        if ((nonCoded==null)||(coded==null)) {
            return false;
        }
        return nonCoded.equals(coded);
    }


}
