package com.example.qubit.d_delivery;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.qubit.d_delivery.connection.*;
import com.example.qubit.d_delivery.connection.JSON_TO_VAL;
import com.example.qubit.d_delivery.connection.STR_TO_JSON;
import com.example.qubit.d_delivery.earthquake.EarthquakeEntry;
import com.example.qubit.d_delivery.earthquake.EarthquakeListAdapter;
import com.example.qubit.d_delivery.enums.FormatValues;
import com.example.qubit.d_delivery.enums.OrderValues;
import com.example.qubit.d_delivery.enums.TimeValue;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/";
    /*query?format=geojson&starttime=2021-03-13&endtime=2022-03-15&minmagnitude=6.5*/

    private ListView listView;
    private UsrViewModel model;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        listView = findViewById( R.id.home_listView );
        List<EarthquakeEntry> items = new ArrayList<>();
        items.add( new EarthquakeEntry() );
        EarthquakeListAdapter adapter = new EarthquakeListAdapter( getApplicationContext(), R.layout.listview_item, items );
        listView.setAdapter( adapter );

        model = new ViewModelProvider( this ).get( UsrViewModel.class );
        model.getEarthquakes().observe( this, earthquakeEntryList -> {
            UpdateUI();
        } );


    }

    private void UpdateUI () {
        EarthquakeListAdapter adapter = new EarthquakeListAdapter( this, R.layout.listview_item, model.getEarthquakes().getValue() );
        listView.setAdapter( adapter );
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.home_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected ( MenuItem item ) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                UpdateUI();
                break;
            default:
                return true;
        }
        return true;
    }

//    public class MyViewModel extends ViewModel {
//
//        private static final String URL =
//                "https://earthquake.usgs.gov/fdsnws/event/1/";
//
//        public List<EarthquakeEntry> earthquakes = new ArrayList<>();
//        public boolean isCreated = false;
//
//
//        public void setEarthquakes(){
//            if(isCreated){
//                return;
//            }
//            NetworkingBackground bgThread = new NetworkingBackground();
//            String readyUrl = setURLAttributes(URL);
//            bgThread.execute(readyUrl);//initialising jsonObject
//        }
//        public List<EarthquakeEntry> getEarthquakes(){
//        if(!isCreated){
//            setEarthquakes();
//        }
//            return earthquakes;
//        }
//
//        private String setURLAttributes(String rootUrl){
//            CreateURL creator = new CreateURL(rootUrl);
//
//            creator.addFormatAttr(FormatValues.JSON);
//            creator.addLimitAttr(500);
//            creator.addMaxMagnitudeAttr(9);
//            creator.addMinMagnitudeAttr(1);
//            creator.addOffsetAttr(1);
//            creator.addOrderAttr(OrderValues.MAG_DSC);
//            creator.reAddStartTimeAttr(TimeValue.YEAR.YEAR_2022, TimeValue.MONTH.MONTH_3, TimeValue.DAY.DAY_15);
//
//            Log.i(TAG, "THE GENERATED URL IS: " + creator.getQuery());
//
//            return creator.getQueryAndResetAll();
//        }
//
//        private class NetworkingBackground extends AsyncTask<String, Void, List<EarthquakeEntry>> {
//            @Override
//            protected List<EarthquakeEntry> doInBackground(String... strUrls) {
//                JSONObject jsonObject = STR_TO_JSON.getJsonObject(strUrls[0]);
//                earthquakes = JSON_TO_VAL.getEntryList(jsonObject, -1);
//                return earthquakes;
//            }
//
//            @Override
//            protected void onPreExecute(){
//                Log.v(TAG, " background thread started");
//            }
//
//            @Override
//            protected void onPostExecute(List<EarthquakeEntry> earthquakes){
//                Log.v(TAG, "background thread ended");
//                if(earthquakes != null) {
//                    isCreated = true;
//                    UpdateUI();
//                }
//            }
//        }
//    }

}