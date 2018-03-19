package com.epam.apartmentbooking.service.dozer.impl;

import org.dozer.BeanFactory;
import org.javamoney.moneta.Money;

/**
 * Created by Serge_Kozik on 4/18/2017.
 */
public class DefaultMonetaryFactory implements BeanFactory {
    @Override
    public Object createBean(Object o, Class<?> aClass, String s) {
        return Money.of(0,"USD");
    }
}
