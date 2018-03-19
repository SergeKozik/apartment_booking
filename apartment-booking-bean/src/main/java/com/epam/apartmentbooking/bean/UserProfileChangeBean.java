package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Roldo on 22.05.2017.
 */
public class UserProfileChangeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String nick;
    private String password;
    private String passwordConfirm;
    private String oldPassword;

    public UserProfileChangeBean() {
    }

    public UserProfileChangeBean(UserProfileBean userProfileBean) {
        this.nick = userProfileBean.getNick();
        this.email = userProfileBean.getEmail();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfileChangeBean that = (UserProfileChangeBean) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (nick != null ? !nick.equals(that.nick) : that.nick != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (passwordConfirm != null ? !passwordConfirm.equals(that.passwordConfirm) : that.passwordConfirm != null)
            return false;
        return oldPassword != null ? oldPassword.equals(that.oldPassword) : that.oldPassword == null;

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passwordConfirm != null ? passwordConfirm.hashCode() : 0);
        result = 31 * result + (oldPassword != null ? oldPassword.hashCode() : 0);
        return result;
    }
}
