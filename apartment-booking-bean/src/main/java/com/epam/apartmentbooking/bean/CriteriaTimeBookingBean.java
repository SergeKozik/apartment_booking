package com.epam.apartmentbooking.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
public class CriteriaTimeBookingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String checkIn;
    private String checkOut;
    private String dateTemplate;

    public CriteriaTimeBookingBean(Locale locale) {
        this.dateTemplate = dateTemplate;
        Date currentDate = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",locale);
        this.checkIn = dateFormat.format(currentDate);
        this.checkOut = dateFormat.format(currentDate);
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getDateTemplate() {
        return dateTemplate;
    }

    public void setDateTemplate(String dateTemplate) {
        this.dateTemplate = dateTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaTimeBookingBean that = (CriteriaTimeBookingBean) o;

        if (checkIn != null ? !checkIn.equals(that.checkIn) : that.checkIn != null) return false;
        if (checkOut != null ? !checkOut.equals(that.checkOut) : that.checkOut != null) return false;
        return dateTemplate != null ? dateTemplate.equals(that.dateTemplate) : that.dateTemplate == null;

    }

    @Override
    public int hashCode() {
        int result = checkIn != null ? checkIn.hashCode() : 0;
        result = 31 * result + (checkOut != null ? checkOut.hashCode() : 0);
        result = 31 * result + (dateTemplate != null ? dateTemplate.hashCode() : 0);
        return result;
    }
}
