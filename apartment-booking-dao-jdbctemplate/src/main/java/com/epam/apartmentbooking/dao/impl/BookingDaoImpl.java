package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.BookingDao;
import com.epam.apartmentbooking.dao.impl.criteria.BookingCriteria;
import com.epam.apartmentbooking.entity.BookingEntity;
import com.epam.apartmentbooking.entity.CriteriaTimeBookingDTO;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
@Repository
public class BookingDaoImpl implements BookingDao {

    private static final String TABLE_NAME = "t_booking";
    private static final String COLUMN_ID = "bo_id";
    private static final String COLUMN_CHECK_IN = "bo_check_in";
    private static final String COLUMN_CHECK_OUT = "bo_check_out";
    private static final String COLUMN_GUESTS = "bo_guests";
    private static final String COLUMN_SUM = "bo_total_sum";
    private static final String COLUMN_DATE_BOOKED = "bo_date_booked";
    private static final String COLUMN_APARTMENT_ID = "t_apartment_ap_id";
    private static final String COLUMN_USER_ID = "t_user_us_id";
    private static final String ALIAS_APARTMENT_ID = "ap_id";
    private static final String SQL_SELECT_BY_USER = "SELECT "+COLUMN_ID+", "+COLUMN_CHECK_IN+
            ", "+COLUMN_CHECK_OUT+", "+COLUMN_SUM+", "+COLUMN_DATE_BOOKED+", "+COLUMN_APARTMENT_ID+", "+COLUMN_USER_ID+
            " FROM "+TABLE_NAME+
            " WHERE "+COLUMN_USER_ID+"=?";
    private static final String SQL_CHECK_ID = "SELECT "+COLUMN_ID+" FROM "+TABLE_NAME+" WHERE "+COLUMN_APARTMENT_ID+"=?";
    private static final String SQL_INSERT_BOOKING = "INSERT INTO "+TABLE_NAME+
            " ("+COLUMN_CHECK_IN+", "+COLUMN_CHECK_OUT+", "+COLUMN_DATE_BOOKED+", "+COLUMN_SUM+", "+COLUMN_GUESTS+", "+COLUMN_APARTMENT_ID+", "+COLUMN_USER_ID+") "+
            "VALUES (TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class BookingMapper implements RowMapper<BookingEntity> {
        @Override
        public BookingEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            BookingEntity entity = new BookingEntity();
            entity.setId(resultSet.getInt(COLUMN_ID));
            entity.setCheckIn(new Date(resultSet.getTimestamp(COLUMN_CHECK_IN).getTime()));
            entity.setCheckOut(new Date(resultSet.getTimestamp(COLUMN_CHECK_OUT).getTime()));
            entity.setTotalSum(Money.of(resultSet.getBigDecimal(COLUMN_SUM),"USD"));
            entity.setDateBooked(new Date(resultSet.getTimestamp(COLUMN_DATE_BOOKED).getTime()));
            entity.setApartmentId(resultSet.getInt(COLUMN_APARTMENT_ID));
            entity.setUserId(resultSet.getInt(COLUMN_USER_ID));
            return entity;
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
    public List<Integer> returnBusyApartmentIdByCriteria(CriteriaTimeBookingDTO timeBookingEntity) {
        BookingCriteria bookingCriteria = BookingCriteria.build();
        bookingCriteria.specifyPeriod(timeBookingEntity.getCheckIn(),timeBookingEntity.getCheckOut(),COLUMN_CHECK_IN,COLUMN_CHECK_OUT);
        String queryString = bookingCriteria.returnSelectSQLQuery(TABLE_NAME,new String[]{COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID});
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

    @Override
    public List<BookingEntity> findByUserId(int userId) {
        List<BookingEntity> result=null;
        try {
            result=this.jdbcTemplate.query(SQL_SELECT_BY_USER,new Object[]{userId},new BookingMapper());
        } catch (DataAccessException e) {
            result=null;
        }
        return result;
    }

    @Override
    public boolean checkPeriod(int apartmentId, CriteriaTimeBookingDTO timeBookingEntity) {
        List<Integer> ids = this.returnBusyApartmentIdByCriteria(timeBookingEntity);
        return !((ids!=null)&&(ids.contains(apartmentId)));
    }

    @Override
    public BookingEntity book(BookingEntity bookingEntity) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String checkInStr = dateFormat.format(bookingEntity.getCheckIn());
        String checkOutStr = dateFormat.format(bookingEntity.getCheckOut());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String nowStr = dateFormat.format(new Date(System.currentTimeMillis()));
        int rows = this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_BOOKING,new String[] {COLUMN_ID});
                        preparedStatement.setString(1,checkInStr);
                        preparedStatement.setString(2,checkOutStr);
                        preparedStatement.setString(3,nowStr);
                        preparedStatement.setBigDecimal(4,Money.from(bookingEntity.getTotalSum()).getNumberStripped());
                        preparedStatement.setInt(5,bookingEntity.getGuests());
                        preparedStatement.setInt(6,bookingEntity.getApartmentId());
                        preparedStatement.setInt(7,bookingEntity.getUserId());
                        return preparedStatement;
                    }
                },
                keyHolder);
        if (rows>0) {
            bookingEntity.setId(keyHolder.getKey().intValue());
            return bookingEntity;
        }
        return null;
    }
}
