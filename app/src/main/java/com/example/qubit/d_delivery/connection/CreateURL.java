package com.example.qubit.d_delivery.connection;

import com.example.qubit.d_delivery.enums.FormatValues;
import com.example.qubit.d_delivery.enums.OrderValues;
import com.example.qubit.d_delivery.enums.TimeValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateURL {


    private static final String M_QUERY            = "query";       /*String*/      /*the features of the earthquakes*/

    private static final String ATTR_END_TIME      = "endtime";     /*long*/        /*default current time*/
    private static final String ATTR_FORMAT        = "format";      /*String*/      /*default quakeml*/
    private static final String ATTR_LIMIT         = "limit";       /*int 1...20k*/ /*limit of results numbers*/
    private static final String ATTR_MAX_MAGNITUDE = "maxmagnitude";/*double*/      /*default null*/
    private static final String ATTR_MIN_MAGNITUDE = "minmagnitude";/*double*/      /*default null*/
    private static final String ATTR_OFFSET        = "offset";      /*int 1...*/    /*Return results starting at the event count specified, starting at 1*/
    private static final String ATTR_ORDER         = "orderby";     /*String*/      /*default time*/
    private static final String ATTR_START_TIME    = "starttime";   /*long*/        /*default before 30 days*/

    private static final String VAL_FORMAT_JSON  = "geojson";
    private static final String VAL_FORMAT_CSV   = "csv";
    private static final String VAL_FORMAT_KML   = "kml";
    private static final String VAL_FORMAT_TEXT  = "text";
    private static final String VAL_FORMAT_XML   = "xml";

    private static final String VAL_ORDER_TIME_DSC      = "time";
    private static final String VAL_ORDER_TIME_ASC      = "time-asc";
    private static final String VAL_ORDER_MAGNITUDE_DSC = "magnitude";
    private static final String VAL_ORDER_MAGNITUDE_ASC = "magnitude-asc";

    private String rootUrl;
    private String query;
    private int attrCount;

    private String endTime;
    private String format;
    private String limit;
    private String maxMag;
    private String minMag;
    private String offset;
    private String order;
    private String startTime;

    private boolean is_endTime;
    private boolean is_format;
    private boolean is_limit;
    private boolean is_maxMag;
    private boolean is_minMag;
    private boolean is_offset;
    private boolean is_order;
    private boolean is_startTime;

    public CreateURL(String rootUrl){
        this.rootUrl = rootUrl;
        if(this.rootUrl.charAt(rootUrl.length() - 1) != '/')
            this.rootUrl += '/';
        initValues();
        initBoolValues();
        attrCount = 0;
    }
    
    private boolean dateIsValid(String date){
        return true;
    }
    private void initValues(){

        query = rootUrl + M_QUERY + "?";
        endTime = ATTR_END_TIME + "=";
        format = ATTR_FORMAT + "=";
        limit = ATTR_LIMIT + "=";
        maxMag = ATTR_MAX_MAGNITUDE + "=";
        minMag = ATTR_MIN_MAGNITUDE + "=";
        offset = ATTR_OFFSET + "=";
        order = ATTR_ORDER + "=";
        startTime = ATTR_START_TIME + "=";
    }
    private void initBoolValues(){
        is_endTime = false;
        is_format = false;
        is_limit = false;
        is_maxMag = false;
        is_minMag = false;
        is_offset = false;
        is_order = false;
        is_startTime = false;
    }
    
    private String getYear(TimeValue.YEAR year){
        switch (year){
            case YEAR_2000: return "2000";
            case YEAR_2001: return "2001";
            case YEAR_2002: return "2002";
            case YEAR_2003: return "2003";
            case YEAR_2004: return "2004";
            case YEAR_2005: return "2005";
            case YEAR_2006: return "2006";
            case YEAR_2007: return "2007";
            case YEAR_2008: return "2008";
            case YEAR_2009: return "2009";
            case YEAR_2010: return "2010";
            case YEAR_2011: return "2011";
            case YEAR_2012: return "2012";
            case YEAR_2013: return "2013";
            case YEAR_2014: return "2014";
            case YEAR_2015: return "2015";
            case YEAR_2016: return "2016";
            case YEAR_2017: return "2017";
            case YEAR_2018: return "2018";
            case YEAR_2019: return "2019";
            case YEAR_2020: return "2020";
            case YEAR_2021: return "2021";
            case YEAR_2022: return "2022";
            case YEAR_2023: return "2023";
            case YEAR_2024: return "2024";
            case YEAR_2025: return "2025";
            case YEAR_2026: return "2026"; 
            case YEAR_2027: return "2027";
            case YEAR_2028: return "2028";
            case YEAR_2029: return "2029";
            case YEAR_2030: return "2030";
            case YEAR_2031: return "2031";
            case YEAR_2032: return "2032";
            case YEAR_2033: return "2033";
            case YEAR_2034: return "2034";
            case YEAR_2035: return "2035";
            case YEAR_2036: return "2036";
            case YEAR_2037: return "2037";
            case YEAR_2038: return "2038";
            case YEAR_2039: return "2039";
            default: return "2022";
        }
    }
    private String getMonth(TimeValue.MONTH month){

        switch (month){

            case MONTH_1:  return "01";
            case MONTH_2:  return "02";
            case MONTH_3:  return "03";
            case MONTH_4:  return "04";
            case MONTH_5:  return "05";
            case MONTH_6:  return "06";
            case MONTH_7:  return "07";
            case MONTH_8:  return "08";
            case MONTH_9:  return "09";
            case MONTH_10: return "10";
            case MONTH_11: return "11";
            case MONTH_12: return "12";
            default: return "01";
        }
    }
    private String getDay(TimeValue.DAY day){
        switch (day){
            case DAY_1:  return "01";
            case DAY_2:  return "02";
            case DAY_3:  return "03";
            case DAY_4:  return "04";
            case DAY_5:  return "05";
            case DAY_6:  return "06";
            case DAY_7:  return "07";
            case DAY_8:  return "08";
            case DAY_9:  return "09";
            case DAY_10: return "10";
            case DAY_11: return "11";
            case DAY_12: return "12";
            case DAY_13: return "13";
            case DAY_14: return "14";
            case DAY_15: return "15";
            case DAY_16: return "16";
            case DAY_17: return "17";
            case DAY_18: return "18";
            case DAY_19: return "19";
            case DAY_20: return "20";
            case DAY_21: return "21";
            case DAY_22: return "22";
            case DAY_23: return "23";
            case DAY_24: return "24";
            case DAY_25: return "25";
            case DAY_26: return "26";
            case DAY_27: return "27";
            case DAY_28: return "28";
            case DAY_29: return "29";
            case DAY_30: return "30";
            case DAY_31: return "31";
            default: return "01";
        }
    }

    //TODO: deleting attributes and modifying them
    public void addEndTimeAttr(Date endDate){
        if(is_endTime)
            return;
        reAddEndTimeAttr(endDate);
    }
    public void addEndTimeAttr(String strEndDate){
        if(is_endTime)
            return;
        reAddEndTimeAttr(strEndDate);
    }
    public void addEndTimeAttr(TimeValue.YEAR year, TimeValue.MONTH month, TimeValue.DAY day){
        if(is_endTime)
            return;
        reAddEndTimeAttr(year, month, day);
    }
    public void addFormatAttr(FormatValues value){
        if(is_format)
            return;
        reAddFormatAttr(value);
    }
    public void addLimitAttr(int lim) {
        if(is_limit)
            return;
        reAddLimitAttr(lim);
    }
    public void addMaxMagnitudeAttr(double max){
        if(is_maxMag)
            return;
        reAddMaxMagnitudeAttr(max);
    }
    public void addMinMagnitudeAttr(double min){
        if(is_minMag)
            return;
        reAddMinMagnitudeAttr(min);
    }
    public void addOffsetAttr(int OFFSET){
        if(is_offset)
            return;
        reAddOffsetAttr(OFFSET);
    }
    public void addOrderAttr(OrderValues value){
        if(is_order)
            return;
        reAddOrderAttr(value);
    }
    public void addStartTimeAttr(Date startDate){
        if(is_startTime)
            return;
        reAddStartTimeAttr(startDate);
    }
    public void addStartTimeAttr(String strStartDate){
        if(is_startTime)
            return;
        reAddStartTimeAttr(strStartDate);
    }
    public void addStartTimeAttr(TimeValue.YEAR year, TimeValue.MONTH month, TimeValue.DAY day){
        if(is_startTime)
            return;
        reAddStartTimeAttr(year, month, day);
    }


    //TODO: deleting attributes and modifying them
    public void reAddEndTimeAttr(Date endDate){
        endTime = ATTR_END_TIME + "=";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        endTime += formatter.format(endDate);
        is_endTime = true;
        return;
    }
    public void reAddEndTimeAttr(String strEndDate){
        if(dateIsValid(strEndDate)) {
            endTime = ATTR_END_TIME + "=";
            endTime += strEndDate;
            is_endTime = true;
        }
    }
    public void reAddEndTimeAttr(TimeValue.YEAR year, TimeValue.MONTH month, TimeValue.DAY day){
        endTime = ATTR_END_TIME + "=";
        endTime += getYear(year) + "-";
        endTime += getMonth(month) + "-";
        endTime += getDay(day);
        is_endTime = true;
    }
    public void reAddFormatAttr(FormatValues value){
        format = ATTR_FORMAT + "=";
        switch (value){
            case CSV: format += VAL_FORMAT_CSV;
                break;
            case JSON: format += VAL_FORMAT_JSON;
                break;
            case KML: format += VAL_FORMAT_KML;
                break;
            case TEXT: format += VAL_FORMAT_TEXT;
                break;
            case XML: format += VAL_FORMAT_XML;
                break;
            default:
                return;//we don't reAdd the  for the query.
        }
        is_format = true;
        return;
    }
    public void reAddLimitAttr(int lim) {
        limit = ATTR_LIMIT + "=";
        if(lim < 1 || lim > 20000){//if the  is not valid, no thing reAdded.
            return;
        } else{
            limit += String.valueOf(lim);
        }
        is_limit = true;
        return;
    }
    public void reAddMaxMagnitudeAttr(double max){
        maxMag = ATTR_MAX_MAGNITUDE + "=";
        if(max < 0) {//if the  is not valid, no thing reAdded.
            return;
        } else {
            maxMag += String.valueOf(max);
        }
        is_maxMag = true;
        return;
    }
    public void reAddMinMagnitudeAttr(double min){
        minMag = ATTR_MIN_MAGNITUDE + "=";
        if(min < 0){//if the  is not valid, no thing reAdded.
            return;
        } else{
            minMag += String.valueOf(min);
        }
        is_minMag = true;
        return;
    }
    public void reAddOffsetAttr(int OFFSET){
        offset = ATTR_OFFSET + "=";
        if(OFFSET < 1) {//if the  is not valid, no thing reAdded.
            return;
        } else {
            offset += String.valueOf(OFFSET);
        }
        is_offset = true;
        return;
    }
    public void reAddOrderAttr(OrderValues value){
        order = ATTR_ORDER + "=";
        switch (value){
            case TIME_DSC: order += VAL_ORDER_TIME_DSC;
                break;
            case TIME_ASC: order += VAL_ORDER_TIME_ASC;
                break;
            case MAG_DSC: order += VAL_ORDER_MAGNITUDE_DSC;
                break;
            case MAG_ASC: order += VAL_ORDER_MAGNITUDE_ASC;
                break;
            default:
                return;//if the  is invalid nothing reAdded
        }
        is_order = true;
        return;
    }
    public void reAddStartTimeAttr(Date startDate){
        startTime = ATTR_START_TIME + "=";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        startTime += formatter.format(startDate);
        is_startTime = true;
        return;
    }
    public void reAddStartTimeAttr(String strStartDate){
        if(dateIsValid(strStartDate)) {
            startTime = ATTR_START_TIME + "=";
            startTime += strStartDate;
            is_startTime = true;
        }
    }
    public void reAddStartTimeAttr(TimeValue.YEAR year, TimeValue.MONTH month, TimeValue.DAY day){
        startTime = ATTR_START_TIME + "=";
        startTime += getYear(year) + "-";
        startTime += getMonth(month) + "-";
        startTime += getDay(day);
        is_startTime = true;
    }
    
    public String getQuery(){
        if(is_endTime){
            query += (attrCount++ !=0 ?"&":"") + endTime;
        }
        if(is_format){
            query += (attrCount++ !=0 ?"&":"") + format;
        }
        if(is_limit){
            query += (attrCount++ !=0 ?"&":"") + limit;
        }
        if(is_maxMag){
            query += (attrCount++ !=0 ?"&":"") + maxMag;
        }
        if(is_minMag){
            query += (attrCount++ !=0 ?"&":"") + minMag;
        }
        if(is_offset){
            query += (attrCount++ !=0 ?"&":"") + offset;
        }
        if(is_order){
            query += (attrCount++ !=0 ?"&":"") + order;
        }
        if(is_startTime){
            query += (attrCount++ !=0 ?"&":"") + startTime;
        }
        return query;
    }
    public String getQueryAndResetAll(){
        String finalQuery = getQuery();
        initValues();
        initBoolValues();
        return finalQuery;
    }
}
