package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.OwnerDao;
import com.epam.apartmentbooking.entity.CriteriaOwnerDTO;
import com.epam.apartmentbooking.entity.OwnerEntity;
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
public class OwnerDaoImpl implements OwnerDao {


    private static final String FIELD_ID = "id";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_ADDRESS = "address";

    @PersistenceContext
    private EntityManager entityManager;

    public OwnerDaoImpl() {
    }

    @Override
    public OwnerEntity findOne(int id) {
        return entityManager.find(OwnerEntity.class,id);
    }

    @Override
    public List<Integer> returnOwnerIdByCriteria(CriteriaOwnerDTO criteriaOwnerEntity) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<OwnerEntity> owners = query.from(OwnerEntity.class);
        query.select(owners.<Integer>get(FIELD_ID));
        String addressPiece = criteriaOwnerEntity.getAddressPiece();
        String titlePiece = criteriaOwnerEntity.getTitlePiece();
        List<Predicate> predicates = new ArrayList<>();
        if ((addressPiece!=null)&&(!addressPiece.isEmpty())) {
            predicates.add(criteriaBuilder.like(owners.get(FIELD_ADDRESS),"%"+addressPiece+"%"));
        }
        if ((titlePiece!=null)&&(!titlePiece.isEmpty())) {
            predicates.add(criteriaBuilder.like(owners.get(FIELD_TITLE),"%"+titlePiece+"%"));
        }
        query.where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));
        return  entityManager.createQuery(query).getResultList();
    }
}
