package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.*;
import com.epam.apartmentbooking.dao.impl.daofilter.*;
import com.epam.apartmentbooking.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */

@Repository
public class ApartmentDaoImpl implements ApartmentDao {

    private static final String TABLE_NAME="t_apartment";
    private static final String TABLE_OWNER_NAME="t_owner";
    private static final String COLUMN_OWNER_ID ="ow_id";
    private static final String COLUMN_ID="ap_id";
    private static final String COLUMN_FOREIGN_OWNER_ID="t_owner_ow_id";
    private static final String COLUMN_OWNER_ADDRESS ="ow_address";
    private static final String COLUMN_OWNER_TITLE ="ow_title";
    private static final String SQL_SELECT_BY_ADDRESS="SELECT "+ COLUMN_OWNER_ID +", "+COLUMN_ID+", "+ COLUMN_OWNER_ADDRESS +
            " FROM "+TABLE_NAME+" ap"+
            " JOIN "+TABLE_OWNER_NAME+" ow ON ap."+COLUMN_FOREIGN_OWNER_ID+"=ow."+COLUMN_OWNER_ID+
            " WHERE ow."+COLUMN_OWNER_ADDRESS+" LIKE ?";
    private static final String SQL_SELECT_OWNER_NAME="SELECT "+COLUMN_OWNER_TITLE+
            " FROM "+TABLE_NAME+" ap"+
            " JOIN "+TABLE_OWNER_NAME+" ow ON ap."+COLUMN_FOREIGN_OWNER_ID+"=ow."+COLUMN_OWNER_ID+
            " WHERE ap."+COLUMN_ID+"=?";
    private static final String SQL_SELECT_BY_ID="SELECT "+ COLUMN_OWNER_ID +", "+COLUMN_ID+
            " FROM "+TABLE_NAME+
            " WHERE "+COLUMN_ID+"=?";

    private JdbcTemplate jdbcTemplate;
    private SpaceDao spaceDao;
    private AmenityDao amenityDao;
    private AvailabilityDao availabilityDao;
    private PriceDao priceDao;
    private OwnerDao ownerDao;
    private BookingDao bookingDao;

    @Autowired
    public ApartmentDaoImpl(JdbcTemplate jdbcTemplate, SpaceDao spaceDao, AmenityDao amenityDao, AvailabilityDao availabilityDao, PriceDao priceDao, OwnerDao ownerDao, BookingDao bookingDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.spaceDao = spaceDao;
        this.amenityDao = amenityDao;
        this.availabilityDao = availabilityDao;
        this.priceDao = priceDao;
        this.ownerDao = ownerDao;
        this.bookingDao = bookingDao;
    }

    private static final class ApartmentMapping implements RowMapper<ApartmentEntity> {

        private SpaceDao innerSpaceDao;
        private AmenityDao innerAmenityDao;
        private AvailabilityDao innerAvailabilityDao;
        private PriceDao innerPriceDao;
        private OwnerDao innerOwnerDao;

        public ApartmentMapping(SpaceDao innerSpaceDao, AmenityDao innerAmenityDao, AvailabilityDao innerAvailabilityDao, PriceDao innerPriceDao, OwnerDao innerOwnerDao) {
            this.innerSpaceDao = innerSpaceDao;
            this.innerAmenityDao = innerAmenityDao;
            this.innerAvailabilityDao = innerAvailabilityDao;
            this.innerPriceDao = innerPriceDao;
            this.innerOwnerDao = innerOwnerDao;
        }

        @Override
        public ApartmentEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            ApartmentEntity apartment = new ApartmentEntity();
            apartment.setId(resultSet.getInt(COLUMN_ID));
            OwnerEntity owner = innerOwnerDao.findOne(resultSet.getInt(COLUMN_OWNER_ID));
            apartment.setOwner(owner);
            SpaceEntity space = innerSpaceDao.findByApartment_Id(apartment.getId());
            apartment.setSpace(space);
            AmenityEntity amenities = innerAmenityDao.findByApartment_Id(apartment.getId());
            apartment.setAmenities(amenities);
            AvailabilityEntity availability = innerAvailabilityDao.findByApartment_Id(apartment.getId());
            apartment.setAvailability(availability);
            PriceEntity prices = innerPriceDao.findByApartment_Id(apartment.getId());
            apartment.setPrice(prices);
            return apartment;
        }
    }

    private static final class ApartmentIdMapping implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            Integer id = resultSet.getInt(COLUMN_ID);
            return id;
        }
    }

    @Override
    public List<ApartmentEntity> showByAddress(String place) {
        List<ApartmentEntity> apartments;
        String statementParameter = place;
        statementParameter = "%"+statementParameter
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![") + "%";

        try {
            apartments = this.jdbcTemplate.query(SQL_SELECT_BY_ADDRESS,
                    new Object[]{statementParameter},
                    new ApartmentMapping(spaceDao,amenityDao,availabilityDao,priceDao,ownerDao));
        } catch (EmptyResultDataAccessException e ) {
            apartments = new ArrayList<>();
        }
        return apartments;
    }

    @Override
    public List<Integer> returnIdByOwnerId(List<Integer> ownerIds) {
        List<Integer> apartmentIds=null;
        if ((ownerIds!=null)&&(!ownerIds.isEmpty())) {
            String queryString = "SELECT "+COLUMN_ID+" FROM "+TABLE_NAME+" WHERE "+COLUMN_FOREIGN_OWNER_ID+" IN ("+ownerIds.get(0).toString();
            for (int ii=1; ii<ownerIds.size(); ii++) {
                queryString=queryString+", "+ownerIds.get(ii).toString();
            }
            queryString=queryString+")";
            try {
                apartmentIds=this.jdbcTemplate.query(queryString,new ApartmentIdMapping());
            } catch (DataAccessException e) {
                apartmentIds=null;
            }
        }
        return apartmentIds;
    }

    @Override
    public String showOwnerNameByAptId(int id) {
        String result = null;
        try {
            result = this.jdbcTemplate.queryForObject(SQL_SELECT_OWNER_NAME,
                    new Object[]{id},
                    String.class);
        } catch (EmptyResultDataAccessException e ) {
            return null;
        }
        return result;
    }

    @Override
    public ApartmentEntity findOne(int id) {
        ApartmentEntity result=null;
        try{
            result=this.jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,new Object[]{id},new ApartmentMapping(spaceDao,amenityDao,availabilityDao,priceDao,ownerDao));
        } catch (EmptyResultDataAccessException e ) {
            result = null;
        }
        return result;
    }

    @Override
    public List<ApartmentEntity> findById(List<Integer> ids) {
        List<ApartmentEntity> result=null;
        if ((ids!=null)&&(!ids.isEmpty())) {
            String queryString = "SELECT "+COLUMN_ID+", "+COLUMN_FOREIGN_OWNER_ID+" AS "+COLUMN_OWNER_ID+" FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" IN ("+ids.get(0).toString();
            for (int ii=1; ii<ids.size(); ii++) {
                queryString=queryString+", "+ids.get(ii).toString();
            }
            queryString=queryString+")";
            try {
                result=this.jdbcTemplate.query(queryString,new ApartmentMapping(spaceDao,amenityDao,availabilityDao,priceDao,ownerDao));
            } catch (DataAccessException e) {
                result=null;
            }
        }
        return result;
    }

    @Override
    public List<ApartmentEntity> showAll() {
        List<ApartmentEntity> result;
        String queryString = "SELECT "+COLUMN_ID+", "+COLUMN_FOREIGN_OWNER_ID+" AS "+COLUMN_OWNER_ID+" FROM "+TABLE_NAME;
        try {
            result=this.jdbcTemplate.query(queryString,new ApartmentMapping(spaceDao,amenityDao,availabilityDao,priceDao,ownerDao));
        } catch (DataAccessException e) {
            result=null;
        }
        return result;
    }

    @Override
    public List<Integer> showAllIds() {
        List<Integer> result;
        String queryString = "SELECT "+COLUMN_ID+" FROM "+TABLE_NAME;
        try {
            result=this.jdbcTemplate.query(queryString,new ApartmentIdMapping());
        } catch (DataAccessException e) {
            result=null;
        }
        return result;
    }

    @Override
    public List<Integer> returnIdByCriteria(CriteriaApartmentDTO criteriaApartmentDTO) {
        ApartmentFilter apartmentFilter = new ApartmentFilter.Builder()
                .registerFilter(new OwnerFilter(ownerDao,this))
                .registerFilter(new AmenityFilter(amenityDao))
                .registerFilter(new AvailabilityFilter(availabilityDao))
                .registerFilter(new PriceFilter(priceDao))
                .registerFilter(new SpaceFilter(spaceDao))
                .registerFilter(new TimeBookingFilter(bookingDao,this))
                .build();
        return apartmentFilter.filterApartmentIds(criteriaApartmentDTO);
    }


}
