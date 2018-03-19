package com.epam.apartmentbooking.dao.impl.daofilter;

import com.epam.apartmentbooking.entity.CriteriaApartmentDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Roldo on 05.07.2017.
 */
public class ApartmentFilter {

    private List<DaoFilterMarker> filters;

    private ApartmentFilter() {
        this.filters = new ArrayList<>();
    }
    private ApartmentFilter(List<DaoFilterMarker> filters) {
        this.filters = filters;
    }

    public static class Builder {

        private List<DaoFilterMarker> builderFilters;

        public Builder() {
            builderFilters = new ArrayList<>();
        }

        public Builder registerFilter(DaoFilterMarker daoFilterMarker) {
            if (daoFilterMarker!=null) {
                builderFilters.add(daoFilterMarker);
            }
            return this;
        }

        public ApartmentFilter build() {
            return new ApartmentFilter(builderFilters);
        }
    }

    public List<Integer> filterApartmentIds(CriteriaApartmentDTO criteriaApartmentDTO) {
        List<Integer> apartmentIds = new ArrayList<>();
        if (!filters.isEmpty()) {
            Iterator<DaoFilterMarker> iterator = filters.iterator();
            apartmentIds = iterator.next().addApartmentIds(apartmentIds, criteriaApartmentDTO);
            while ((iterator.hasNext())&&(!apartmentIds.isEmpty())) {
                apartmentIds = iterator.next().filterApartmentIds(apartmentIds, criteriaApartmentDTO);
            }
        }
        return apartmentIds;
    }
}
