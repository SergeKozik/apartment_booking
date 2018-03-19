package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.bean.UserBeanForUserService;
import com.epam.apartmentbooking.dao.UserDao;
import com.epam.apartmentbooking.entity.UserEntity;
import com.epam.apartmentbooking.service.UserService;
import com.epam.apartmentbooking.service.dozer.EntityBeanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Roldo on 22.05.2017.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EntityBeanConverter converter;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByEmail(s);
        if (userEntity==null) {
            throw new UsernameNotFoundException(messageSource.getMessage("message.label.error.login-fault",null, LocaleContextHolder.getLocale())+" "+s);
        }
        UserBeanForUserService userBeanForUserService = converter.convertToBean(userEntity,UserBeanForUserService.class);
        return userBeanForUserService;
    }
}
