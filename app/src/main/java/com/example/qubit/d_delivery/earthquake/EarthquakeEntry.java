package com.example.qubit.d_delivery.earthquake;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EarthquakeEntry {
    private double magnitude;
    private String place;
    private long date;
    private String url;


    public EarthquakeEntry(double magnitude, String place, long date, String url) {
        this.magnitude = magnitude;
        this.place = place;
        this.date = date;
        this.url = url;
    }
    public EarthquakeEntry() {
        magnitude = 0.0;
        place = "Null Place";
        date = 1;
        url = "Null URL";
    }

    public double getMagnitude() {
        return magnitude;
    }
    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public long getDate() {
        return date;
    }
    public void setDate(long date) {
        this.date = date;
    }

    public String getFormattedDate(String template){
        SimpleDateFormat formatter = new SimpleDateFormat(template);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        Date date = calendar.getTime();
        return formatter.format(date);
    }
    public String getFormattedDate(){
        return getFormattedDate("yyyy-mm-dd");
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
