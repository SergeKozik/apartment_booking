package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.*;
import com.epam.apartmentbooking.dao.impl.daofilter.*;
import com.epam.apartmentbooking.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/11/2017.
 */

@Repository
public class ApartmentDaoImpl implements ApartmentDao {

    private static final String ID_FIELD = "id";
    private static final String OWNER_FIELD = "owner";
    private static final String OWNER_TITLE_FIELD = "title";
    private static final String OWNER_ID_FIELD = "id";
    private static final String OWNER_ADDRESS_FIELD = "address";


    @PersistenceContext
    private EntityManager entityManager;

    private SpaceDao spaceDao;
    private AmenityDao amenityDao;
    private AvailabilityDao availabilityDao;
    private PriceDao priceDao;
    private OwnerDao ownerDao;
    private BookingDao bookingDao;

    @Autowired
    public ApartmentDaoImpl(SpaceDao spaceDao, AmenityDao amenityDao, AvailabilityDao availabilityDao, PriceDao priceDao, OwnerDao ownerDao, BookingDao bookingDao) {
        this.spaceDao = spaceDao;
        this.amenityDao = amenityDao;
        this.availabilityDao = availabilityDao;
        this.priceDao = priceDao;
        this.ownerDao = ownerDao;
        this.bookingDao = bookingDao;
    }

    @Override
    public List<ApartmentEntity> showByAddress(String place) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ApartmentEntity> apartmentQuery = criteriaBuilder.createQuery(ApartmentEntity.class);
        Root<ApartmentEntity> apartments = apartmentQuery.from(ApartmentEntity.class);
        apartmentQuery.select(apartments);

        Subquery<Integer> ownerIds = apartmentQuery.subquery(Integer.class);
        Root<OwnerEntity> owners = ownerIds.from(OwnerEntity.class);
        ownerIds.select(owners.<Integer>get(OWNER_ID_FIELD));
        ownerIds.where(criteriaBuilder.like(owners.get(OWNER_ADDRESS_FIELD),place));

        apartmentQuery.where(criteriaBuilder.equal(apartments.get(OWNER_FIELD).get(OWNER_ID_FIELD),ownerIds));

        return entityManager.createQuery(apartmentQuery).getResultList();
    }

    @Override
    public List<Integer> returnIdByOwnerId(List<Integer> ownerIds) {

        if ((ownerIds==null)||(ownerIds.isEmpty())) {
            return null;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> idsQuery = criteriaBuilder.createQuery(Integer.class);
        Root<ApartmentEntity> apartments = idsQuery.from(ApartmentEntity.class);
        idsQuery.select(apartments.<Integer>get(ID_FIELD));

        CriteriaBuilder.In inClause = criteriaBuilder.in(apartments.get(OWNER_FIELD).get(OWNER_ID_FIELD));
        for (Integer tmpId:ownerIds) {
            inClause.value(tmpId);
        }
        idsQuery.where(inClause);
        return entityManager.createQuery(idsQuery).getResultList();
    }

    @Override
    public String showOwnerNameByAptId(int id) {
        String result = null;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<ApartmentEntity> apartments = query.from(ApartmentEntity.class);
        Join<ApartmentEntity,OwnerEntity> joinApartments = apartments.join(OWNER_FIELD);
        query.select(joinApartments.<String>get(OWNER_FIELD).get(OWNER_TITLE_FIELD)).where(criteriaBuilder.equal(apartments.get(ID_FIELD),id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public ApartmentEntity findOne(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ApartmentEntity> query = criteriaBuilder.createQuery(ApartmentEntity.class);
        Root<ApartmentEntity> apartments = query.from(ApartmentEntity.class);
        query.select(apartments).where(criteriaBuilder.equal(apartments.<Integer>get(ID_FIELD),id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public List<ApartmentEntity> findById(List<Integer> ids) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ApartmentEntity> query = criteriaBuilder.createQuery(ApartmentEntity.class);
        Root<ApartmentEntity> apartments = query.from(ApartmentEntity.class);
        query.select(apartments);
        CriteriaBuilder.In inClause = criteriaBuilder.in(apartments.get(ID_FIELD));
        for (Integer tmpId:ids) {
            inClause.value(tmpId);
        }
        query.where(inClause);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<ApartmentEntity> showAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ApartmentEntity> query = criteriaBuilder.createQuery(ApartmentEntity.class);
        Root<ApartmentEntity> apartments = query.from(ApartmentEntity.class);
        query.select(apartments);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Integer> showAllIds() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<ApartmentEntity> apartments = query.from(ApartmentEntity.class);
        query.select(apartments.<Integer>get(ID_FIELD));
        return entityManager.createQuery(query).getResultList();
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
