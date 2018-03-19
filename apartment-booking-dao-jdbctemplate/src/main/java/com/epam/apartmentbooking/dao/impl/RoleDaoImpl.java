package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.RoleDao;
import com.epam.apartmentbooking.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Serge_Kozik on 6/14/2017.
 */

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final String TABLE_NAME = "t_roles";
    private static final String COLUMN_ID = "ro_id";
    private static final String COLUMN_NAME = "ro_name";
    private static final String SQL_SELECT_BY_ID = "SELECT "+COLUMN_ID+", "+COLUMN_NAME+" FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+"=?";
    private static final String SQL_SELECT_BY_NAME = "SELECT "+COLUMN_ID+", "+COLUMN_NAME+" FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+"=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class RoleMapper implements RowMapper<RoleEntity> {
        @Override
        public RoleEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(resultSet.getInt(COLUMN_ID));
            roleEntity.setName(resultSet.getString(COLUMN_NAME));
            return roleEntity;
        }
    }

    @Override
    public RoleEntity findOne(int id) {
        RoleEntity roleEntity;
        try {
            roleEntity = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,new Object[]{id}, new RoleMapper());
        } catch (EmptyResultDataAccessException e) {
            roleEntity=null;
        }
        return roleEntity;
    }

    @Override
    public RoleEntity findByName(String name) {
        RoleEntity roleEntity;
        try {
            roleEntity = jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME,new Object[]{name},new RoleMapper());
        } catch (EmptyResultDataAccessException e) {
            roleEntity=null;
        }
        return roleEntity;
    }
}
