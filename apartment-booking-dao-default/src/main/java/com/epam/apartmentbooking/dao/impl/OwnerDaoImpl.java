package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.OwnerDao;
import com.epam.apartmentbooking.dao.impl.criteria.OwnerCriteria;
import com.epam.apartmentbooking.entity.CriteriaOwnerDTO;
import com.epam.apartmentbooking.entity.OwnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
@Repository
public class OwnerDaoImpl implements OwnerDao {


    private static final String TABLE_NAME = "t_owner";
    private static final String COLUMN_ID = "ow_id";
    private static final String COLUMN_TITLE = "ow_title";
    private static final String COLUMN_ADDRESS = "ow_address";
    private static final String SQL_SELECT_BY_ID="SELECT ow_id, ow_title, ow_address FROM t_owner WHERE ow_id = ?";

    private JdbcTemplate jdbcTemplate;

    private static final class OwnerMapper implements RowMapper<OwnerEntity> {
        @Override
        public OwnerEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            OwnerEntity ownerEntity = new OwnerEntity();
            ownerEntity.setId(resultSet.getInt(COLUMN_ID));
            ownerEntity.setAddress(resultSet.getString(COLUMN_ADDRESS));
            ownerEntity.setTitle(resultSet.getString(COLUMN_TITLE));
            return ownerEntity;
        }
    }

    private static final class OwnerIdMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            Integer id = resultSet.getInt(COLUMN_ID);
            return id;
        }
    }

    @Autowired
    public OwnerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public OwnerEntity findOne(int id) {
        OwnerEntity entity;
        try {
            entity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    new Object[]{id},
                    new OwnerMapper());
        } catch (EmptyResultDataAccessException e ) {
            entity = null;
        }
        return entity;
    }

    @Override
    public List<Integer> returnOwnerIdByCriteria(CriteriaOwnerDTO criteriaOwnerEntity) {
        OwnerCriteria ownerCriteria = OwnerCriteria.build();
        if (criteriaOwnerEntity.getAddressPiece()!=null) {
            ownerCriteria.specifyAddress(criteriaOwnerEntity.getAddressPiece(),COLUMN_ADDRESS);
        }
        if (criteriaOwnerEntity.getTitlePiece()!=null) {
            ownerCriteria.specifyTitle(criteriaOwnerEntity.getTitlePiece(),COLUMN_TITLE);
        }
        String queryString = ownerCriteria.returnSelectSQLQuery(TABLE_NAME,new String[]{COLUMN_ID});
        if (queryString.isEmpty()) {
            return null;
        }
        List<Integer> result;
        try{
            result = this.jdbcTemplate.query(queryString,
                    new OwnerIdMapper());
        } catch (EmptyResultDataAccessException e ) {
            result = new ArrayList<>();
        }
        return result;

    }
}
