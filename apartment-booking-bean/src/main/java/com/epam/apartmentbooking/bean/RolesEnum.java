package com.epam.apartmentbooking.bean;

import java.util.Map;

/**
 * Created by Serge_Kozik on 6/14/2017.
 */
public enum RolesEnum {
    USER("label.role.user"),
    ANONYMOUS("label.role.anonymous");

    private static Map<String, RolesEnum> valuesMap;

    static {
        valuesMap.put("user",RolesEnum.USER);
        valuesMap.put("anonymous",RolesEnum.ANONYMOUS);
    }

    private String propertiesCode;

    RolesEnum(String propertiesCode) {
        this.propertiesCode = propertiesCode;
    }

    public String getPropertiesCode() {
        return propertiesCode;
    }

    public RolesEnum getRolesEnum(String nameLowerCase) {
        return valuesMap.get(nameLowerCase);
    }
}
