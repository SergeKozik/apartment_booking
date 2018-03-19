package com.epam.apartmentbooking.service;


import com.epam.apartmentbooking.bean.*;

/**
 * Created by Serge_Kozik on 4/3/2017.
 */
public interface UserService {
    public UserProfileBean registerUser(UserRegisterBean newUser);
    public UserProfileBean findOne(int userId);
    public UserProfileBean findByEmail(String email);
    public UserProfileBean loginUser(UserLoginBean loginBean);
    public PasswordChangingBean restorePassword(String email);
    public UserProfileBean editProfile(UserProfileBean profileBean, UserProfileChangeBean changeBean);
    public String codePassword(String nonCoded);
}
