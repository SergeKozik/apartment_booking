package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.SpaceDao;
import com.epam.apartmentbooking.entity.CriteriaSpaceDTO;
import com.epam.apartmentbooking.entity.SpaceEntity;
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
public class SpaceDaoImpl implements SpaceDao {

    private static final String ID_ENTITY_FIELD = "apartmentId";
    private static final String ACCOMMODATES_FIELD = "accommodates";
    private static final String ROOM_TYPE_FIELD = "roomType";
    private static final String BEDS_FIELD = "beds";

    @PersistenceContext
    private EntityManager entityManager;

    public SpaceDaoImpl() {
    }

    @Override
    public SpaceEntity findByApartment_Id(int apartmentId) {
        return entityManager.find(SpaceEntity.class,apartmentId);
    }

    @Override
    public List<Integer> returnApartmentIdByCriteria(CriteriaSpaceDTO criteriaSpaceEntity) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<SpaceEntity> spaces = query.from(SpaceEntity.class);
        query.select(spaces.<Integer>get(ID_ENTITY_FIELD));
        List<Predicate> predicates = new ArrayList<>();

        int accomodatesMin = criteriaSpaceEntity.getAccomodatesMin();
        int accomodatesMax = criteriaSpaceEntity.getAccomodatesMax();

        if ((accomodatesMin>0)||(accomodatesMax>0)) {
            predicates.add(criteriaBuilder.between(spaces.<Integer>get(ACCOMMODATES_FIELD),accomodatesMin,accomodatesMax));
        }

        int bedsMax = criteriaSpaceEntity.getBedsMax();
        int bedsMin = criteriaSpaceEntity.getBedsMin();

        if ((bedsMin>0)||(bedsMax>0)) {
            predicates.add(criteriaBuilder.between(spaces.<Integer>get(BEDS_FIELD),bedsMin,bedsMax));
        }

        List<String> roomTypes = criteriaSpaceEntity.getRoomTypePiece();

        if ((roomTypes!=null)&&(!roomTypes.isEmpty())) {
            CriteriaBuilder.In inClause = criteriaBuilder.in(spaces.get(ROOM_TYPE_FIELD));
            for (String roomType:roomTypes) {
                inClause.value(roomType);
            }
            predicates.add(inClause);
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }
}
