package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.RoleDao;
import com.epam.apartmentbooking.dao.UserDao;
import com.epam.apartmentbooking.entity.RoleEntity;
import com.epam.apartmentbooking.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Serge_Kozik on 4/5/2017.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final String TABLE_NAME = "t_user";
    private static final String ID_COLUMN = "us_id";
    private static final String NAME_COLUMN = "us_name";
    private static final String EMAIL_COLUMN = "us_email";
    private static final String PASSW_COLUMN = "us_password";
    private static final String ENABLED_COLUMN = "us_enabled";
    private static final String ROLE_COLUMN = "t_roles_ro_id";
    private static final String SQL_SELECT_BY_ID="SELECT "+ID_COLUMN+", "+NAME_COLUMN+", "+EMAIL_COLUMN+", "+PASSW_COLUMN+", "+ENABLED_COLUMN+", "+ROLE_COLUMN+
            " FROM "+TABLE_NAME+" WHERE "+ID_COLUMN+"=?";
    private static final String SQL_UPDATE_USER="UPDATE "+TABLE_NAME+" SET "+
            NAME_COLUMN+"=?, "+EMAIL_COLUMN+"=?, "+PASSW_COLUMN+"=?, "+ENABLED_COLUMN+"=?, "+ROLE_COLUMN+"=? WHERE "+ID_COLUMN+"=?";
    private static final String SQL_INSERT_USER="INSERT INTO "+
            TABLE_NAME+" ("+NAME_COLUMN+", "+EMAIL_COLUMN+", "+PASSW_COLUMN+", "+ENABLED_COLUMN+", "+ROLE_COLUMN+" ) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_BY_EMAIL="SELECT "+ID_COLUMN+", "+NAME_COLUMN+", "+EMAIL_COLUMN+", "+PASSW_COLUMN+", "+ENABLED_COLUMN+", "+ROLE_COLUMN+
            " FROM "+TABLE_NAME+" WHERE "+EMAIL_COLUMN+" = ?";
    private static final String SQL_SELECT_BY_EMAIL_PASSW="SELECT "+ID_COLUMN+", "+NAME_COLUMN+", "+EMAIL_COLUMN+", "+PASSW_COLUMN+", "+ENABLED_COLUMN+", "+ROLE_COLUMN+
            " FROM "+TABLE_NAME+" WHERE "+EMAIL_COLUMN+"=? AND "+PASSW_COLUMN+"=?";
    private static final String SQL_SELECT_BY_NAME_PASSW="SELECT "+ID_COLUMN+", "+NAME_COLUMN+", "+EMAIL_COLUMN+", "+PASSW_COLUMN+", "+ENABLED_COLUMN+", "+ROLE_COLUMN+
            " FROM "+TABLE_NAME+" WHERE "+NAME_COLUMN+" = ? AND "+PASSW_COLUMN+"=?";

    private JdbcTemplate jdbcTemplate;
    private RoleDao roleDao;

    private static final class UserMapper implements RowMapper<UserEntity> {

        private RoleDao roleDao;

        public UserMapper(RoleDao roleDao) {
            this.roleDao = roleDao;
        }

        @Override
        public UserEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(resultSet.getInt(ID_COLUMN));
            userEntity.setNick(resultSet.getString(NAME_COLUMN));
            userEntity.setEmail(resultSet.getString(EMAIL_COLUMN));
            userEntity.setPassword(resultSet.getString(PASSW_COLUMN));
            userEntity.setEnabled(resultSet.getBoolean(ENABLED_COLUMN));
            RoleEntity roleEntity = this.roleDao.findOne(resultSet.getInt(ROLE_COLUMN));
            userEntity.setRole(roleEntity);
            return userEntity;
        }
    }

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, RoleDao roleDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleDao = roleDao;
    }

    @Override
    public UserEntity findOne(int id) {
        UserEntity entity;
        try {
            entity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    new Object[]{id},
                    new UserMapper(roleDao));
        } catch (EmptyResultDataAccessException e ) {
            entity = null;
        }
        return entity;
    }

    @Override
    public UserEntity save(UserEntity entity) {
        if ((entity==null)||(entity.getRole()==null)) {
            return null;
        }
        int rows=0;
        int userId = entity.getId();
        if (userId>0) {
            rows = this.jdbcTemplate.update(
                    SQL_UPDATE_USER,
                    entity.getNick(),
                    entity.getEmail(),
                    entity.getPassword(),
                    entity.isEnabled(),
                    entity.getRole().getId(),
                    entity.getId()
                    );
        } else {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            rows = this.jdbcTemplate.update(
                    new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER, new String[]{ID_COLUMN});
                            ps.setString(1,entity.getNick());
                            ps.setString(2,entity.getEmail());
                            ps.setString(3,entity.getPassword());
                            ps.setBoolean(4,entity.isEnabled());
                            ps.setInt(5,entity.getRole().getId());
                            return ps;
                        }
                    },
                    keyHolder);
            userId = keyHolder.getKey().intValue();
        }
        if (rows>0) {
            UserEntity result;
            try {
                result = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                        new Object[]{userId},
                        new UserMapper(roleDao));
            } catch (EmptyResultDataAccessException e) {
                result=null;
            }
            return result;
        }
        return null;
    }

    @Override
    public UserEntity findByEmail(String email) {
        UserEntity entity = null;
        try{
            entity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL,
                    new Object[]{email},
                    new UserMapper(roleDao));
        } catch (EmptyResultDataAccessException e ) {
            entity = null;
        }

        return entity;
    }

    @Override
    public UserEntity findByEmailAndPassword(String email, String password) {
        UserEntity entity = null;
        try{
            entity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL_PASSW,
                    new Object[]{email,password},
                    new UserMapper(roleDao));
        } catch (EmptyResultDataAccessException e ) {
            entity = null;
        }
        return entity;
    }

    @Override
    public UserEntity findByNickAndPassword(String nick, String password) {
        UserEntity entity = null;
        try{
            entity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME_PASSW,
                    new Object[]{nick,password},
                    new UserMapper(roleDao));
        } catch (EmptyResultDataAccessException e ) {
            entity = null;
        }
        return entity;
    }
}
