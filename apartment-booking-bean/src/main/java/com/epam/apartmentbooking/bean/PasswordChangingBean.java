package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/3/2017.
 */
public class PasswordChangingBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private int userId;
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;

    public PasswordChangingBean() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordChangingBean that = (PasswordChangingBean) o;

        if (userId != that.userId) return false;
        if (oldPassword != null ? !oldPassword.equals(that.oldPassword) : that.oldPassword != null) return false;
        if (newPassword != null ? !newPassword.equals(that.newPassword) : that.newPassword != null) return false;
        return newPasswordConfirm != null ? newPasswordConfirm.equals(that.newPasswordConfirm) : that.newPasswordConfirm == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (oldPassword != null ? oldPassword.hashCode() : 0);
        result = 31 * result + (newPassword != null ? newPassword.hashCode() : 0);
        result = 31 * result + (newPasswordConfirm != null ? newPasswordConfirm.hashCode() : 0);
        return result;
    }
}
