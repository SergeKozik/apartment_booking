package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.AmenityDao;
import com.epam.apartmentbooking.dao.impl.criteria.AmenityCriteria;
import com.epam.apartmentbooking.entity.AmenityEntity;
import com.epam.apartmentbooking.entity.CriteriaAmenityDTO;
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
public class AmenityDaoImpl implements AmenityDao{
    private static final String TABLE_NAME = "t_amenities";
    private static final String COLUMN_APARTMENT_ID = "t_apartment_ap_id";
    private static final String ALIAS_APARTMENT_ID = "ap_id";
    private static final String COLUMN_WIRELESS_NAME = "am_wireless";
    private static final String COLUMN_FREE_PARKING = "am_free_parking";

    private static final String SQL_SELECT_BY_APARTMENT="SELECT "+COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID+", "+COLUMN_WIRELESS_NAME+", "+COLUMN_FREE_PARKING+" FROM " +
            TABLE_NAME+" WHERE "+COLUMN_APARTMENT_ID+"=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AmenityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class AmenityMapper implements RowMapper<AmenityEntity> {
        @Override
        public AmenityEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            AmenityEntity amenities = new AmenityEntity();
            amenities.setApartmentId(resultSet.getInt(ALIAS_APARTMENT_ID));
            amenities.setFreeParking(resultSet.getBoolean(COLUMN_FREE_PARKING));
            amenities.setWifi(resultSet.getBoolean(COLUMN_WIRELESS_NAME));
            return amenities;
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
    public AmenityEntity findByApartment_Id(int apartmentId) {
        AmenityEntity amenityEntity;
        try{
            amenityEntity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_APARTMENT,
                    new Object[]{apartmentId},
                    new AmenityMapper());
        } catch (EmptyResultDataAccessException e ) {
            amenityEntity = null;
        }
        return amenityEntity;
    }

    @Override
    public List<Integer> returnApartmentIdByCriteria(CriteriaAmenityDTO criteriaAmenityDTO) {
        AmenityCriteria amenityCriteria = AmenityCriteria.build();
        amenityCriteria.specifyFreeParking(criteriaAmenityDTO.isFreeParkingFlag(),COLUMN_FREE_PARKING);
        amenityCriteria.specifyWifi(criteriaAmenityDTO.isWifiFlag(),COLUMN_WIRELESS_NAME);
        String queryString = amenityCriteria.returnSelectSQLQuery(TABLE_NAME,new String[]{COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID});
        if (queryString.isEmpty()) {
            return null;
        }
        List<Integer> result;
        try{
            result = this.jdbcTemplate.query(queryString,
                    new ApartmentIdMapper());
        } catch (EmptyResultDataAccessException e ) {
            result = new ArrayList<>();
        }
        return result;
    }
}
