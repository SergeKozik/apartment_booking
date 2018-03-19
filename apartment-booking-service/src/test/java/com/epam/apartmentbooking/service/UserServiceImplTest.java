package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.bean.PasswordChangingBean;
import com.epam.apartmentbooking.bean.UserProfileBean;
import com.epam.apartmentbooking.bean.UserRegisterBean;
import com.epam.apartmentbooking.dao.UserDao;
import com.epam.apartmentbooking.entity.UserEntity;
import com.epam.apartmentbooking.service.dozer.EntityBeanConverter;
import com.epam.apartmentbooking.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Serge_Kozik on 4/6/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private EntityBeanConverter converter;

    private UserServiceImpl userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userDao,converter);
    }

    @Test
    public void restorePasswordTest() {
        UserEntity mockedBeforeSaveUser = new UserEntity();
        mockedBeforeSaveUser.setPassword("old password");
        UserEntity mockedSavedUser = new UserEntity();

        when(userDao.findByEmail("testEmail")).thenReturn(mockedBeforeSaveUser);
        when(userDao.save(isA(UserEntity.class))).thenReturn(mockedSavedUser);

        PasswordChangingBean newPassword = userService.restorePassword("testEmail");
        assertTrue("Password changed wrong",!newPassword.getNewPassword().equals("old password"));
    }
}
