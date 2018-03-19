package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.PriceDao;
import com.epam.apartmentbooking.entity.CriteriaPriceDTO;
import com.epam.apartmentbooking.entity.PriceEntity;
import org.springframework.stereotype.Repository;

import javax.money.MonetaryAmount;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
@Repository
public class PriceDaoImpl implements PriceDao {

    private static final String ID_ENTITY_FIELD = "apartmentId";
    private static final String DAILY_ENTITY_FIELD = "daily";

    @PersistenceContext
    private EntityManager entityManager;

    public PriceDaoImpl() {
    }

    @Override
    public PriceEntity findByApartment_Id(int apartmentId) {
        return entityManager.find(PriceEntity.class,apartmentId);
    }

    @Override
    public List<Integer> returnApartmentIdByCriteria(CriteriaPriceDTO criteriaPriceEntity) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<PriceEntity> prices = query.from(PriceEntity.class);
        query.select(prices.<Integer>get(ID_ENTITY_FIELD));

        BigDecimal dailyMin = criteriaPriceEntity.getDailyMin();
        BigDecimal dailyMax = criteriaPriceEntity.getDailyMax();
        List<Predicate> predicates = new ArrayList<>();
        if ((dailyMin.compareTo(BigDecimal.ZERO)!=0)||(dailyMax.compareTo(BigDecimal.ZERO)!=0)) {
            predicates.add(criteriaBuilder.between(prices.<BigDecimal>get(DAILY_ENTITY_FIELD),dailyMin,dailyMax));
        }
        double monthlyMin = criteriaPriceEntity.getMonthlyDiscountMin();
        double monthlyMax = criteriaPriceEntity.getMonthlyDiscountMax();
        if ((monthlyMin!=0)||(monthlyMax!=0)) {
            predicates.add(criteriaBuilder.between(prices.<Double>get("monthlyDiscount"),monthlyMin,monthlyMax));
        }
        double weeklyMin = criteriaPriceEntity.getWeeklyDiscountMin();
        double weeklyMax = criteriaPriceEntity.getWeeklyDiscountMax();
        if ((weeklyMin!=0)||(weeklyMax!=0)) {
            predicates.add(criteriaBuilder.between(prices.<Double>get("weeklyDiscount"),weeklyMin,weeklyMax));
        }
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return  entityManager.createQuery(query).getResultList();
    }
}
