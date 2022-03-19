package com.example.qubit.d_delivery;

import com.example.qubit.d_delivery.enums.FormatValues;
import com.example.qubit.d_delivery.enums.OrderValues;

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

    CreateURL(String rootUrl){
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
