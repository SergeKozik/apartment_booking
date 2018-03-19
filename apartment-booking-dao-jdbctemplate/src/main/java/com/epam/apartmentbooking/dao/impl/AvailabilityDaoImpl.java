package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.AvailabilityDao;
import com.epam.apartmentbooking.dao.impl.criteria.AvailabilityCriteria;
import com.epam.apartmentbooking.entity.AvailabilityEntity;
import com.epam.apartmentbooking.entity.CriteriaAvailabilityDTO;
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
public class AvailabilityDaoImpl implements AvailabilityDao {
    private static final String TABLE_NAME = "t_availability";
    private static final String COLUMN_APARTMENT_ID = "t_apartment_ap_id";
    private static final String ALIAS_APARTMENT_ID = "ap_id";
    private static final String COLUMN_MIN_NIGHTS = "av_min_night";

    private static final String SQL_SELECT_BY_APARTMENT="SELECT "+COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID+", "+COLUMN_MIN_NIGHTS+
            " FROM "+TABLE_NAME+" WHERE "+COLUMN_APARTMENT_ID+"=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AvailabilityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class AvailabilityMapper implements RowMapper<AvailabilityEntity> {
        @Override
        public AvailabilityEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            AvailabilityEntity availability = new AvailabilityEntity();
            availability.setApartmentId(resultSet.getInt(ALIAS_APARTMENT_ID));
            availability.setMinNights(resultSet.getInt(COLUMN_MIN_NIGHTS));
            return availability;
        }
    }

    private static final class ApartmentIdMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            Integer id = resultSet.getInt(ALIAS_APARTMENT_ID);
            return id;
        }
    }

    @Override
    public AvailabilityEntity findByApartment_Id(int apartmentId) {
        AvailabilityEntity availabilityEntity;
        try{
            availabilityEntity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_APARTMENT,
                    new Object[]{apartmentId},
                    new AvailabilityMapper());
        } catch (EmptyResultDataAccessException e ) {
            availabilityEntity = null;
        }
        return availabilityEntity;
    }

    @Override
    public List<Integer> returnApartmentIdByCriteria(CriteriaAvailabilityDTO criteriaAvailabilityDTO) {
        String queryString = AvailabilityCriteria.build()
                .specifyMinNights(criteriaAvailabilityDTO.getMinNightsMin(), criteriaAvailabilityDTO.getMinNightsMax(),COLUMN_MIN_NIGHTS)
                .returnSelectSQLQuery(TABLE_NAME,new String[]{COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID});
        List<Integer> result;
        if (queryString.isEmpty()) {
            return null;
        }
        try{
            result = this.jdbcTemplate.query(queryString,
                    new ApartmentIdMapper());
        } catch (EmptyResultDataAccessException e ) {
            result = new ArrayList<>();
        }
        return result;
    }
}
