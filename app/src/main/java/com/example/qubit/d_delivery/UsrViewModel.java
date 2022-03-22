package com.example.qubit.d_delivery;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.loader.content.AsyncTaskLoader;

import com.example.qubit.d_delivery.connection.CreateURL;
import com.example.qubit.d_delivery.connection.JSON_TO_VAL;
import com.example.qubit.d_delivery.connection.STR_TO_JSON;
import com.example.qubit.d_delivery.earthquake.EarthquakeEntry;
import com.example.qubit.d_delivery.enums.FormatValues;
import com.example.qubit.d_delivery.enums.OrderValues;
import com.example.qubit.d_delivery.enums.TimeValue;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class UsrViewModel extends ViewModel {

    private static final String URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/";

    public MutableLiveData<List<EarthquakeEntry>> earthquakes;

    public LiveData<List<EarthquakeEntry>> getEarthquakes () {
        if (earthquakes == null) {
            earthquakes = new MutableLiveData<>();
            loadEarthquakes();
        }
        return earthquakes;
    }

    public void loadEarthquakes () {
        BG_Thread networkThread = new BG_Thread();
        networkThread.execute( URL );
    }

    private class BG_Thread extends AsyncTask<String, Void, List<EarthquakeEntry>> {

        @Override
        protected List<EarthquakeEntry> doInBackground ( String... URLs ) {
            String readyURL = setURL_ATTR( URLs[0] );
            JSONObject jsonObject = STR_TO_JSON.getJsonObject(readyURL);
            return JSON_TO_VAL.getEntryList( jsonObject , -1 );
        }

        protected void onPostExecute(List<EarthquakeEntry> earthquakesList){
            earthquakes.setValue( earthquakesList );
        }
        private String setURL_ATTR (String URL) {
            CreateURL creator = new CreateURL( URL );

            creator.addFormatAttr( FormatValues.JSON );
            creator.addLimitAttr( 500 );
            creator.addMaxMagnitudeAttr( 9 );
            creator.addMinMagnitudeAttr( 1 );
            creator.addOffsetAttr( 1 );
            creator.addOrderAttr( OrderValues.MAG_DSC );
            creator.reAddStartTimeAttr( TimeValue.YEAR.YEAR_2022, TimeValue.MONTH.MONTH_3, TimeValue.DAY.DAY_15 );

            Log.i( TAG, "THE GENERATED URL IS: " + creator.getQuery() );

            return creator.getQuery();
        }

        protected void afterFinishing(){

        }
    }
}
