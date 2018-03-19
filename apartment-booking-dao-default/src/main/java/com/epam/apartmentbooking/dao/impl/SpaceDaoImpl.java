package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.SpaceDao;
import com.epam.apartmentbooking.dao.impl.criteria.SpaceCriteria;
import com.epam.apartmentbooking.entity.CriteriaSpaceDTO;
import com.epam.apartmentbooking.entity.SpaceEntity;
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
public class SpaceDaoImpl implements SpaceDao {

    private static final String TABLE_NAME = "t_space";
    private static final String COLUMN_APARTMENT_ID = "t_apartment_ap_id";
    private static final String ALIAS_APARTMENT_ID = "ap_id";
    private static final String COLUMN_BEDS = "sp_beds";
    private static final String COLUMN_ACCOMODATES = "sp_accommodates";
    private static final String COLUMN_ROOMTYPE = "sp_room_type";
    private static final String SQL_SELECT_BY_APARTMENT="SELECT "+COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID+
            ", "+COLUMN_ACCOMODATES+", "+COLUMN_BEDS+", "+COLUMN_ROOMTYPE+" FROM "+TABLE_NAME+" WHERE "+COLUMN_APARTMENT_ID+"=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SpaceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class SpaceMapper implements RowMapper<SpaceEntity> {
        @Override
        public SpaceEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            SpaceEntity space = new SpaceEntity();
            space.setApartmentId(resultSet.getInt(ALIAS_APARTMENT_ID));
            space.setAccommodates(resultSet.getInt(COLUMN_ACCOMODATES));
            space.setBeds(resultSet.getInt(COLUMN_BEDS));
            space.setRoomType(resultSet.getString(COLUMN_ROOMTYPE));
            return space;
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
    public SpaceEntity findByApartment_Id(int apartmentId) {
        SpaceEntity spaceEntity;
        try{
            spaceEntity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_APARTMENT,
                    new Object[]{apartmentId},
                    new SpaceMapper());
        } catch (EmptyResultDataAccessException e ) {
            spaceEntity = null;
        }
        return spaceEntity;
    }

    @Override
    public List<Integer> returnApartmentIdByCriteria(CriteriaSpaceDTO criteriaSpaceEntity) {
        SpaceCriteria spaceCriteria = SpaceCriteria.build();
        spaceCriteria.specifyAccomodates(criteriaSpaceEntity.getAccomodatesMin(),criteriaSpaceEntity.getAccomodatesMax(),COLUMN_ACCOMODATES);
        spaceCriteria.specifyBeds(criteriaSpaceEntity.getBedsMin(),criteriaSpaceEntity.getBedsMax(),COLUMN_BEDS);
        if (criteriaSpaceEntity.getRoomTypePiece()!=null) {
            spaceCriteria.specifyRoomType(criteriaSpaceEntity.getRoomTypePiece(),COLUMN_ROOMTYPE);
        }
        String queryString = spaceCriteria.returnSelectSQLQuery(TABLE_NAME,new String[]{COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID});
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
