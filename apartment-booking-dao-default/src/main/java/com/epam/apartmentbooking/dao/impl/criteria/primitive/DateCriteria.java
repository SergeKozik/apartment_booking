package com.epam.apartmentbooking.dao.impl.criteria.primitive;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
public class DateCriteria implements CriteriaInterface {

    private Date minDate;
    private Date maxDate;
    private String columnNameIn;
    private String columnNameOut;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String minDateStr;
    private String maxDateStr;

    public DateCriteria(Date minDate, Date maxDate, String columnNameIn, String columnNameOut) {
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.minDateStr = dateFormat.format(minDate);
        this.maxDateStr = dateFormat.format(maxDate);
        this.columnNameIn = columnNameIn;
        this.columnNameOut = columnNameOut;
    }

    @Override
    public String generateQueryClause() {

        return "(("+columnNameIn+">TO_DATE('"+minDateStr+"','YYYY-MM-DD HH24:MI:SS') AND "+columnNameIn+"<TO_DATE('"+maxDateStr+"','YYYY-MM-DD HH24:MI:SS')) OR (" +
                columnNameOut+"<TO_DATE('"+maxDateStr+"','YYYY-MM-DD HH24:MI:SS') AND "+columnNameOut+">TO_DATE('"+minDateStr+"','YYYY-MM-DD HH24:MI:SS')))" +
                " OR ((TO_DATE('" +
                minDateStr+"','YYYY-MM-DD HH24:MI:SS')>"+columnNameIn+" AND TO_DATE('"+minDateStr+"','YYYY-MM-DD HH24:MI:SS')<"+columnNameOut+") OR (TO_DATE('" +
                maxDateStr+"','YYYY-MM-DD HH24:MI:SS')<"+columnNameOut+" AND TO_DATE('"+maxDateStr+"','YYYY-MM-DD HH24:MI:SS')>"+columnNameIn+"))";
    }
}
