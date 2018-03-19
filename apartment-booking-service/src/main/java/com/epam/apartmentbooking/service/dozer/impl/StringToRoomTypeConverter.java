package com.epam.apartmentbooking.service.dozer.impl;

import com.epam.apartmentbooking.bean.RoomTypeEnum;
import com.epam.apartmentbooking.service.SharedResourceHolder;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Serge_Kozik on 7/7/2017.
 */
public class StringToRoomTypeConverter extends DozerConverter<String,RoomTypeEnum> {

    @Autowired
    private SharedResourceHolder sharedResourceHolder;

    public StringToRoomTypeConverter() {
        super(String.class,RoomTypeEnum.class);
    }

    @Override
    public RoomTypeEnum convertTo(String s, RoomTypeEnum roomTypeEnum) {
        return RoomTypeEnum.getTypeEnum(s);
    }

    @Override
    public String convertFrom(RoomTypeEnum roomTypeEnum, String s) {
        return roomTypeEnum.name().toLowerCase();
    }
}
