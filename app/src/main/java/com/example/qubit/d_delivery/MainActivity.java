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
        runOnUiThread( new Runnable() {
            @Override
            public void run () {

            }
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

}