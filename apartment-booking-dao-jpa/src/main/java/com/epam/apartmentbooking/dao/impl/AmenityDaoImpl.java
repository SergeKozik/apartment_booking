package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.AmenityDao;
import com.epam.apartmentbooking.entity.AmenityEntity;
import com.epam.apartmentbooking.entity.CriteriaAmenityDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
@Repository
public class AmenityDaoImpl implements AmenityDao{

    private static final String ID_ENTITY_FIELD = "apartmentId";
    private static final String WIFI_ENTITY_FIELD = "wifi";
    private static final String PARKING_ENTITY_FIELD = "freeParking";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AmenityEntity findByApartment_Id(int apartmentId) {
        return entityManager.find(AmenityEntity.class,apartmentId);
    }

    @Override
    public List<Integer> returnApartmentIdByCriteria(CriteriaAmenityDTO criteriaAmenityDTO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> idQuery = criteriaBuilder.createQuery(Integer.class);
        Root<AmenityEntity> amenities = idQuery.from(AmenityEntity.class);
        idQuery.select(amenities.<Integer>get(ID_ENTITY_FIELD));
        List<Predicate> predicates = new ArrayList<>();
        if (criteriaAmenityDTO.isWifiFlag()) {
            predicates.add(criteriaBuilder.equal(amenities.get(WIFI_ENTITY_FIELD),Boolean.TRUE));
        }
        if (criteriaAmenityDTO.isFreeParkingFlag()) {
            predicates.add(criteriaBuilder.equal(amenities.get(PARKING_ENTITY_FIELD),Boolean.TRUE));
        }
        idQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));
        return  entityManager.createQuery(idQuery).getResultList();
    }
}
