package com.epam.apartmentbooking.service.dozer;

import java.util.List;

/**
 * Created by Serge on 11.02.2017.
 */
public interface EntityBeanConverter {
    <E, B> B convertToBean(E entity, Class<B> beanClass);

    <E, B> List<B> convertToBeanList(Iterable<E> entities, Class<B> beanClass);

    <E, B> E convertToEntity(B bean, Class<E> entityClass);

}
