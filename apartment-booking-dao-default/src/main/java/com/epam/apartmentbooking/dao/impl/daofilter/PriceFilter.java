package com.epam.apartmentbooking.dao.impl.daofilter;

import com.epam.apartmentbooking.dao.PriceDao;
import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;
import com.epam.apartmentbooking.entity.CriteriaPriceDTO;

import java.util.List;

/**
 * Created by Roldo on 05.07.2017.
 */
public class PriceFilter implements DaoFilterMarker {

    private PriceDao priceDao;

    public PriceFilter(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    @Override
    public List<Integer> filterApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaPriceDTO criteriaPriceDTO = criteriaApartmentDTO.getPrice();
        if (criteriaPriceDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = priceDao.returnApartmentIdByCriteria(criteriaPriceDTO);
        if (filteredIds!=null) {
            apartmentIds.retainAll(filteredIds);
        }
        return apartmentIds;
    }

    @Override
    public List<Integer> addApartmentIds(List<Integer> apartmentIds, CriteriaApartmentDTO criteriaApartmentDTO) {
        CriteriaPriceDTO criteriaPriceDTO = criteriaApartmentDTO.getPrice();
        if (criteriaPriceDTO ==null) {
            return apartmentIds;
        }
        List<Integer> filteredIds = priceDao.returnApartmentIdByCriteria(criteriaPriceDTO);
        if ((filteredIds!=null)&&(!filteredIds.isEmpty())) {
            apartmentIds.addAll(filteredIds);
        }
        return apartmentIds;
    }
}
