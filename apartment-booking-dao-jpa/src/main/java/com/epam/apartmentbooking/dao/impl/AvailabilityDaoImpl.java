package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.AvailabilityDao;
import com.epam.apartmentbooking.entity.AvailabilityEntity;
import com.epam.apartmentbooking.entity.CriteriaAvailabilityDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
@Repository
public class AvailabilityDaoImpl implements AvailabilityDao {

    private static final String ID_ENTITY_FIELD = "apartmentId";
    private static final String MIN_NIGHTS_FIELD = "minNights";

    @PersistenceContext
    private EntityManager entityManager;

    public AvailabilityDaoImpl() {
    }

    @Override
    public AvailabilityEntity findByApartment_Id(int apartmentId) {
        return entityManager.find(AvailabilityEntity.class,apartmentId);
    }

    @Override
    public List<Integer> returnApartmentIdByCriteria(CriteriaAvailabilityDTO criteriaAvailabilityDTO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<AvailabilityEntity> availabilities = query.from(AvailabilityEntity.class);
        query.select(availabilities.<Integer>get(ID_ENTITY_FIELD));
        int nightsMin = criteriaAvailabilityDTO.getMinNightsMin();
        int nightsMax = criteriaAvailabilityDTO.getMinNightsMax();
        if ((nightsMin!=0)||(nightsMax!=0)) {
            query.where(criteriaBuilder.between(availabilities.<Integer>get(MIN_NIGHTS_FIELD),nightsMin,nightsMax));
        }
        return  entityManager.createQuery(query).getResultList();
    }
}
