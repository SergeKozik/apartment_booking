package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.bean.LanguageEnum;
import com.epam.apartmentbooking.bean.RoomTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Serge_Kozik on 5/11/2017.
 */
@Component
public class SharedResourceHolder {

    private EnumMap<LanguageEnum,Map<String,String>> localizedRoomTypes;

    @Autowired
    public SharedResourceHolder(MessageSource messageSource) {
        localizedRoomTypes = new EnumMap<LanguageEnum, Map<String, String>>(LanguageEnum.class);
        for (LanguageEnum languageEnum:LanguageEnum.values()) {
            Map<String,String> tmpMap = new HashMap<>();
            Locale locale = Locale.forLanguageTag(languageEnum.getNameLocale());
            for (RoomTypeEnum roomTypeEnum:RoomTypeEnum.values()) {
                tmpMap.put(roomTypeEnum.name().toLowerCase(),messageSource.getMessage(roomTypeEnum.getPropertiesCode(), null, locale));
            }
            localizedRoomTypes.put(languageEnum,tmpMap);
        }
    }

    public synchronized Map<String,String> returnLocalizedRoomTypes(Locale locale) {
        LanguageEnum languageEnum = LanguageEnum.getEnumByLanguageCode(locale.getLanguage());
        return localizedRoomTypes.get(languageEnum);
    }
}
