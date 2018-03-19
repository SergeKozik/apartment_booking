package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.PriceDao;
import com.epam.apartmentbooking.dao.impl.criteria.PriceCriteria;
import com.epam.apartmentbooking.entity.CriteriaPriceDTO;
import com.epam.apartmentbooking.entity.PriceEntity;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
@Repository
public class PriceDaoImpl implements PriceDao {

    private static final String TABLE_NAME = "t_prices";
    private static final String COLUMN_APARTMENT_ID = "t_apartment_ap_id";
    private static final String ALIAS_APARTMENT_ID = "ap_id";
    private static final String COLUMN_DAILY = "pr_daily";
    private static final String COLUMN_WEEKLY_DISCOUNT = "pr_weekly_discount";
    private static final String COLUMN_MONTHLY_DISCOUNT = "pr_monthly_discount";
    private static final String SQL_SELECT_BY_APARTMENT="SELECT "+COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID+", "+COLUMN_DAILY+", "+COLUMN_WEEKLY_DISCOUNT+", "+COLUMN_MONTHLY_DISCOUNT +
            " FROM "+TABLE_NAME+" WHERE "+COLUMN_APARTMENT_ID+"=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PriceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class PriceMapper implements RowMapper<PriceEntity> {
        @Override
        public PriceEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            PriceEntity prices = new PriceEntity();
            prices.setApartmentId(resultSet.getInt(ALIAS_APARTMENT_ID));
            prices.setMonthlyDiscount(resultSet.getDouble(COLUMN_MONTHLY_DISCOUNT));
            prices.setWeeklyDiscount(resultSet.getDouble(COLUMN_WEEKLY_DISCOUNT));
            BigDecimal totalSum = resultSet.getBigDecimal(COLUMN_DAILY);
            BigDecimal monetaryAmount = Money.of(totalSum,"USD");
            prices.setDaily(monetaryAmount);
            return prices;
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
    public PriceEntity findByApartment_Id(int apartmentId) {
        PriceEntity priceEntity;
        try{
            priceEntity = this.jdbcTemplate.queryForObject(SQL_SELECT_BY_APARTMENT,
                    new Object[]{apartmentId},
                    new PriceMapper());
        } catch (EmptyResultDataAccessException e ) {
            priceEntity = null;
        }
        return priceEntity;
    }

    @Override
    public List<Integer> returnApartmentIdByCriteria(CriteriaPriceDTO criteriaPriceEntity) {
        String queryString = PriceCriteria.build()
                .specifyDaily(criteriaPriceEntity.getDailyMin(),criteriaPriceEntity.getDailyMax(),COLUMN_DAILY)
                .specifyMonthlyDiscount(criteriaPriceEntity.getMonthlyDiscountMin(),criteriaPriceEntity.getMonthlyDiscountMax(),COLUMN_MONTHLY_DISCOUNT)
                .specifyWeeklyDiscount(criteriaPriceEntity.getWeeklyDiscountMin(),criteriaPriceEntity.getWeeklyDiscountMax(),COLUMN_WEEKLY_DISCOUNT)
                .returnSelectSQLQuery(TABLE_NAME,new String[]{COLUMN_APARTMENT_ID+" AS "+ALIAS_APARTMENT_ID});
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
