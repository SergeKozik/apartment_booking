package com.epam.apartmentbooking.bean;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Serge_Kozik on 6/14/2017.
 */
public class RoleAsAuthority implements GrantedAuthority {

    private String roleName;

    public RoleAsAuthority() {
        this.roleName="";
    }

    public RoleAsAuthority(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
