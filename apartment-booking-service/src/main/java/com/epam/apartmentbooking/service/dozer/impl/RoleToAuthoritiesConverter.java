package com.epam.apartmentbooking.service.dozer.impl;

import com.epam.apartmentbooking.bean.RoleAsAuthority;
import com.epam.apartmentbooking.dao.RoleDao;
import com.epam.apartmentbooking.entity.RoleEntity;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 6/14/2017.
 */
public class RoleToAuthoritiesConverter extends DozerConverter<RoleEntity,List> {

    @Autowired
    private RoleDao roleDao;

    public RoleToAuthoritiesConverter() {
        super(RoleEntity.class, List.class);
    }

    @Override
    public List convertTo(RoleEntity roleEntity, List list) {
        if (roleEntity==null) {
            return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new RoleAsAuthority(roleEntity.getName()));
        return authorities;
    }

    @Override
    public RoleEntity convertFrom(List list, RoleEntity roleEntity) {
        if ((list==null)||(list.isEmpty())) {
            return null;
        }
        GrantedAuthority authority = (GrantedAuthority) list.get(0);
        return roleDao.findByName(authority.getAuthority());
    }
}
