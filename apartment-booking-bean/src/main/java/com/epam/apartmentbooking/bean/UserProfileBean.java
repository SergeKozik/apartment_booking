package com.epam.apartmentbooking.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 4/3/2017.
 */
public class UserProfileBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nick;
    private String email;
    private String role;
    private boolean enabled;

    public UserProfileBean() {
    }

    public UserProfileBean(int id, String nick, String email) {
        this.id = id;
        this.nick = nick;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfileBean that = (UserProfileBean) o;

        if (id != that.id) return false;
        if (enabled != that.enabled) return false;
        if (nick != null ? !nick.equals(that.nick) : that.nick != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return role != null ? role.equals(that.role) : that.role == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
