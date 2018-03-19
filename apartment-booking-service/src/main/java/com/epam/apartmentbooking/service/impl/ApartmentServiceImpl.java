package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.bean.ApartmentViewBean;
import com.epam.apartmentbooking.bean.CriteriaApartmentBean;
import com.epam.apartmentbooking.dao.*;
import com.epam.apartmentbooking.entity.ApartmentEntity;
import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;
import com.epam.apartmentbooking.service.ApartmentService;
import com.epam.apartmentbooking.service.dozer.EntityBeanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {
    
    private ApartmentDao apartmentDao;
    private AmenityDao amenityDao;
    private AvailabilityDao availabilityDao;
    private OwnerDao ownerDao;
    private PriceDao priceDao;
    private SpaceDao spaceDao;
    private BookingDao bookingDao;
    private EntityBeanConverter converter;

    @Autowired
    public ApartmentServiceImpl(ApartmentDao apartmentDao, AmenityDao amenityDao, AvailabilityDao availabilityDao, OwnerDao ownerDao, PriceDao priceDao, SpaceDao spaceDao, BookingDao bookingDao, EntityBeanConverter converter) {
        this.apartmentDao = apartmentDao;
        this.amenityDao = amenityDao;
        this.availabilityDao = availabilityDao;
        this.ownerDao = ownerDao;
        this.priceDao = priceDao;
        this.spaceDao = spaceDao;
        this.bookingDao = bookingDao;
        this.converter = converter;
    }

    @Override
    public List<ApartmentViewBean> filterAvailable(CriteriaApartmentBean criteriaBean) {
        CriteriaApartmentDTO criteriaDTO = converter.convertToEntity(criteriaBean,CriteriaApartmentDTO.class);
        List<Integer> resultApartmentIds = apartmentDao.returnIdByCriteria(criteriaDTO);
        if (resultApartmentIds.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ApartmentEntity> entities = apartmentDao.findById(resultApartmentIds);
            List<ApartmentViewBean> result = converter.convertToBeanList(entities,ApartmentViewBean.class);
            return result;
        }
    }

    @Override
    public List<ApartmentViewBean> showAll() {
        //List<ApartmentEntity> entities = apartmentDao.showAll();
        List<ApartmentEntity> entities = apartmentDao.showAll();
        List<ApartmentViewBean> result = converter.convertToBeanList(entities,ApartmentViewBean.class);
        return result;
    }

    @Override
    public ApartmentViewBean showOne(int id) {
        ApartmentEntity entity = apartmentDao.findOne(id);
        if (entity==null) {
            return null;
        }
        ApartmentViewBean result = converter.convertToBean(entity,ApartmentViewBean.class);
        return result;
    }
}
