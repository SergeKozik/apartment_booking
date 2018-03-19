package com.epam.apartmentbooking.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serge_Kozik on 5/11/2017.
 */
public enum LanguageEnum {
    ENGLISH("label.language-english","en-GB"),
    RUSSIAN("label.language-russian","ru-RU");

    private String propertiesCode;
    private String nameLocale;
    private static Map<String,LanguageEnum> mapByLanguageLocale = new HashMap<>();

    static {
        mapByLanguageLocale.put("ru",RUSSIAN);
        mapByLanguageLocale.put("en",ENGLISH);
    }

    LanguageEnum(String propertiesCode, String nameLocale) {
        this.propertiesCode = propertiesCode;
        this.nameLocale = nameLocale;
    }

    public String getPropertiesCode() {
        return propertiesCode;
    }

    public String getNameLocale() {
        return nameLocale;
    }

    public static LanguageEnum getEnumByLanguageCode(String languageCode) {
        return mapByLanguageLocale.get(languageCode);
    }
}
