package com.example.qubit.d_delivery;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qubit.d_delivery.enums.FormatValues;
import com.example.qubit.d_delivery.enums.OrderValues;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/";
    /*query?format=geojson&starttime=2021-03-13&endtime=2022-03-15&minmagnitude=6.5*/

    private JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonObject = null;
        NetworkingBackground bgThread = new NetworkingBackground();
        
        String readyUrl = setURLAttributes(URL);
        
        bgThread.execute(readyUrl);//initialising jsonObject

    }

    private String setURLAttributes(String rootUrl){
        CreateURL creator = new CreateURL(rootUrl);

//        creator.addEndTimeAttr("2022-03-19");

        creator.addFormatAttr(FormatValues.JSON);

        creator.addLimitAttr(500);

        creator.addMaxMagnitudeAttr(9);

        creator.addMinMagnitudeAttr(1);

        creator.addOffsetAttr(1);

        creator.addOrderAttr(OrderValues.MAG_DSC);

        creator.reAddStartTimeAttr("2022-03-10");

        Log.i(TAG, "THE GENERATED URL IS: " + creator.getQuery());

        return creator.getQueryAndResetAll();
    }
    
    public class NetworkingBackground extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strUrls) {
            JSONObject jsonObject = STR_TO_JSON.getJsonObject(strUrls[0]);
            return jsonObject;
        }

        @Override
        protected void onPreExecute(){
            Log.v(TAG, " background thread started");
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject){
            Log.v(TAG, "background thread ended");
            if(jsonObject != null) {
                setListViewAdapter(jsonObject);
            }
        }
    }

    private void setListViewAdapter(JSONObject jsonObject) {
        ListView listView = findViewById(R.id.home_listView);
        List<EarthquakeEntry> items = JSON_TO_VAL.getEntryList(jsonObject, -1);
        EarthquakeListAdapter adapter = new EarthquakeListAdapter(getApplicationContext(), R.layout.listview_item, items);
        listView.setAdapter(adapter);
    }

}