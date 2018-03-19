package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.BookingDao;
import com.epam.apartmentbooking.entity.BookingEntity;
import com.epam.apartmentbooking.entity.CriteriaTimeBookingDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
@Repository
public class BookingDaoImpl implements BookingDao {

    private static final String TABLE_NAME = "t_booking";
    private static final String COLUMN_CHECK_IN = "bo_check_in";
    private static final String COLUMN_CHECK_OUT = "bo_check_out";
    private static final String COLUMN_APARTMENT_ID = "t_apartment_ap_id";
    private static final String JPQL_SELECT_BY_USER = "SELECT b FROM BookingEntity b WHERE b.userId = :userId";

    @PersistenceContext
    private EntityManager entityManager;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BookingDaoImpl() {
    }

    @Override
    public List<Integer> returnBusyApartmentIdByCriteria(CriteriaTimeBookingDTO timeBookingEntity) {

        String minDateStr = dateFormat.format(timeBookingEntity.getCheckIn());
        String maxDateStr = dateFormat.format(timeBookingEntity.getCheckOut());
        String SELECT_BUSY_ID = "SELECT "+COLUMN_APARTMENT_ID+" FROM "+TABLE_NAME+" WHERE (("+COLUMN_CHECK_IN+">TO_DATE('"+minDateStr+"','YYYY-MM-DD HH24:MI:SS') AND "+COLUMN_CHECK_IN+"<TO_DATE('"+maxDateStr+"','YYYY-MM-DD HH24:MI:SS')) OR (" +
                COLUMN_CHECK_OUT+"<TO_DATE('"+maxDateStr+"','YYYY-MM-DD HH24:MI:SS') AND "+COLUMN_CHECK_OUT+">TO_DATE('"+minDateStr+"','YYYY-MM-DD HH24:MI:SS')))" +
                " OR ((TO_DATE('" +
                minDateStr+"','YYYY-MM-DD HH24:MI:SS')>"+COLUMN_CHECK_IN+" AND TO_DATE('"+minDateStr+"','YYYY-MM-DD HH24:MI:SS')<"+COLUMN_CHECK_OUT+") OR (TO_DATE('" +
                maxDateStr+"','YYYY-MM-DD HH24:MI:SS')<"+COLUMN_CHECK_OUT+" AND TO_DATE('"+maxDateStr+"','YYYY-MM-DD HH24:MI:SS')>"+COLUMN_CHECK_IN+"))";

        Query query = entityManager.createNativeQuery(SELECT_BUSY_ID,Integer.class);
        List<Integer> result = query.getResultList();
        return result;
    }

    @Override
    public List<BookingEntity> findByUserId(int userId) {

        TypedQuery<BookingEntity> query = entityManager.createQuery(JPQL_SELECT_BY_USER,BookingEntity.class);
        query.setParameter("userId",userId);
        return query.getResultList();
    }

    @Override
    public boolean checkPeriod(int apartmentId, CriteriaTimeBookingDTO timeBookingEntity) {
        List<Integer> ids = this.returnBusyApartmentIdByCriteria(timeBookingEntity);
        return !((ids!=null)&&(ids.contains(apartmentId)));
    }

    @Override
    public BookingEntity book(BookingEntity bookingEntity) {
        return entityManager.merge(bookingEntity);
    }
}
