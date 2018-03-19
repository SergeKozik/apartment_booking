package com.epam.apartmentbooking.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serge_Kozik on 5/11/2017.
 */
public enum RoomTypeEnum {
    ENTIRE_HOME("label.room-type-house"),
    PRIVATE_ROOM("label.room-type-room"),
    SHARED_ROOM("label.room-type-shared");

    private static Map<String,RoomTypeEnum> valuesMap = new HashMap<>();

    static {
        valuesMap.put("entire_home",ENTIRE_HOME);
        valuesMap.put("private_room",PRIVATE_ROOM);
        valuesMap.put("shared_room",SHARED_ROOM);
    }

    private String propertiesCode;

    RoomTypeEnum(String propertiesCode) {
        this.propertiesCode = propertiesCode;
    }

    public String getPropertiesCode() {
        return propertiesCode;
    }

    public static RoomTypeEnum getTypeEnum(String nameLowerCase) {
        return valuesMap.get(nameLowerCase);
    }
}
