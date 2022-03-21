package com.example.qubit.d_delivery.connection;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.qubit.d_delivery.earthquake.EarthquakeEntry;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSON_TO_VAL {
    private static int max;
    private static int count;

    //keys for data, /////structure:features in root then index then properties that has mag, place, time and url
    //./features[]/index/properties{}/mag - place - time - url
    static private final String FEATURES_KEY = "features";
    static private final String PROPERTIES_KEY = "properties";
    static private final String MAGNITUDE_KEY = "mag";
    static private final String PLACE_KEY = "place";
    static private final String DATE_KEY = "time";
    static private final String URL_KEY = "url";

    //keys for information, /////structure: information in root then count;
    static private final String INFO_KEY = "metadata";
    static private final String COUNT_KEY = "count";

    private static JSONArray getFeatures(JSONObject jsonObject){
        JSONArray features = null;
        try { features = jsonObject.getJSONArray(FEATURES_KEY);}
        catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "get Features from the json object, Failed to get Features.. parsing json canceled");
        }
        return features;
    }
    private static EarthquakeEntry getEntryItem(JSONObject property){
        EarthquakeEntry item = new EarthquakeEntry();
        try {
            item.setMagnitude(property.getDouble(MAGNITUDE_KEY));
            item.setPlace(property.getString(PLACE_KEY));
            item.setDate(property.getLong(DATE_KEY));
            item.setUrl(property.getString(URL_KEY));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "cannot get on of  (" + MAGNITUDE_KEY + "/" + PLACE_KEY + "/" + DATE_KEY + "/" + URL_KEY + ") item skipped");
            return null;
        }
        return item;
    }
    private static List<EarthquakeEntry> getEntryList(JSONArray features) {
        List<EarthquakeEntry> items = new ArrayList();
        //if max is negative then the condition will be i<features.length() (max is not valid)
        //  otherwise the condition will be i<features.length() && i<max    (max is valid)
        for(int i=0; i<features.length() && (i<max || max<0); i++){
            JSONObject featuresItem = null;
            try {featuresItem = features.getJSONObject(i);}
            catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "Cannot get item with index " + i + " form features array, item skipped");
                continue;
            }
            JSONObject property = null;
            if(featuresItem != null){
                try {property = featuresItem.getJSONObject(PROPERTIES_KEY);}
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Cannot get properties object form item with index " + i + " item skipped");
                    continue;
                }
            }
            if(property != null){
                EarthquakeEntry item = getEntryItem(property);
                if(item != null) {
                    items.add(item);
                }
            }
        }
        return items;
    }

    public static List<EarthquakeEntry> getEntryList(JSONObject jsonObject, int maxNum) {
        max = maxNum;
        JSONArray features = getFeatures(jsonObject);
        List<EarthquakeEntry> earthquakeEntryList = getEntryList(features);

        try {
            JSONObject info = jsonObject.getJSONObject(INFO_KEY);
            count = info.getInt(COUNT_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return earthquakeEntryList;
    }
    public static int getCount(JSONObject jsonObject){
        count = -1;
        try {
            JSONObject info = jsonObject.getJSONObject(INFO_KEY);
            count = info.getInt(COUNT_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return count;
    }
}
