package com.epam.apartmentbooking.bean;

import com.epam.apartmentbooking.bean.validation.PasswordMatches;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/3/2017.
 */
@PasswordMatches
public class UserRegisterBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nick;

    @Email
    private String email;

    @NotNull(message = "{message.error.password-null}")
    private String password;

    private String passwordConfirm;

    public UserRegisterBean() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRegisterBean that = (UserRegisterBean) o;

        if (nick != null ? !nick.equals(that.nick) : that.nick != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return passwordConfirm != null ? passwordConfirm.equals(that.passwordConfirm) : that.passwordConfirm == null;

    }

    @Override
    public int hashCode() {
        int result = nick != null ? nick.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passwordConfirm != null ? passwordConfirm.hashCode() : 0);
        return result;
    }
}
