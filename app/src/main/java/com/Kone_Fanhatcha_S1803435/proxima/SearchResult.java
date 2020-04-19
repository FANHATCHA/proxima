package com.Kone_Fanhatcha_S1803435.proxima;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class SearchResult extends AppCompatActivity {

    TextView largestEarthLocation;
    TextView largestEarthMagnitude;
    TextView deepestEarthLocation;
    TextView deepestEarthKm;
    TextView shallowestEarthLocation;
    TextView shallowestEarthKm;
    TextView dateSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //Action bar | toolbar | logo icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_location_on_black_24dp);

        largestEarthLocation = findViewById(R.id.largest_earth_location);
        largestEarthMagnitude = findViewById(R.id.largest_earth_magnitude);

        deepestEarthLocation = findViewById(R.id.deepest_earthquake_location);
        deepestEarthKm = findViewById(R.id.deepest_earthquake_km);

        shallowestEarthLocation = findViewById(R.id.shallowest_earthquake_location);
        shallowestEarthKm = findViewById(R.id.shallowest_earthquake_km);

        dateSelected = findViewById(R.id.date_selected);

        Intent intent = getIntent();

        /**
         * Source: https://stackoverflow.com/questions/26552839/java-lang-classcastexception-java-util-hashmap-cannot-be-cast-to-com-pau-imapno
         */

        HashMap<String,Double> largestEarthquake= (HashMap<String, Double>) intent.getSerializableExtra("largest_earthquake");
        HashMap<String, Integer> deepestEarthquake = (HashMap<String, Integer>) intent.getSerializableExtra("deepest_earthquake");
        HashMap<String, Integer> shallowestEarthquake = (HashMap<String, Integer>) intent.getSerializableExtra("shallowest_earthquake");

        String dateParsed = intent.getStringExtra("date_selected");

        dateSelected.setText(dateParsed);
        /*************************Largest earthquake location/magnitude starts ******************************/
        String rawLargestLocation = largestEarthquake.keySet().toString();
        String finalLargestLocation = rawLargestLocation.substring(1, rawLargestLocation.length()-1);

        String rawLargestMagnitude = largestEarthquake.values().toString();
        String finalLargestMagnitude = rawLargestMagnitude.substring(1, rawLargestMagnitude.length()-1);

        largestEarthLocation.setText(" - Location" + " : " + finalLargestLocation);
        largestEarthMagnitude.setText(" - Magnitude" + " : " + finalLargestMagnitude);

        /*************************Largest earthquake location/magnitude ends *******************************************/

        /*************************deepest earthquake location/magnitude starts ******************************/
        String rawDeepestLocation = deepestEarthquake.keySet().toString();
        String finalDeepestLocation = rawDeepestLocation.substring(1, rawDeepestLocation.length()-1);

        String rawDeepestKm = deepestEarthquake.values().toString();
        String finalDeepestKm = rawDeepestKm.substring(1, rawDeepestKm.length()-1);

        deepestEarthLocation.setText(" - Location" + " : " + finalDeepestLocation);
        deepestEarthKm.setText(" - Depth" + " : " + finalDeepestKm + " " +  "Km");

        /*************************deepest earthquake location/magnitude ends *******************************************/

        /*************************shallowest earthquake location/magnitude starts ******************************/
        String rawShallowestLocation = shallowestEarthquake.keySet().toString();
        String finalShallowestLocation = rawShallowestLocation.substring(1, rawShallowestLocation.length()-1);

        String rawShallowestKm = shallowestEarthquake.values().toString();
        String finalShallowestKm = rawShallowestKm.substring(1, rawShallowestKm.length()-1);

        shallowestEarthLocation.setText(" - Location" + " : " + finalShallowestLocation);
        shallowestEarthKm.setText(" - Depth" + " : " + finalShallowestKm +  " " + "Km");

        /*************************deepest earthquake location/magnitude ends *******************************************/



    }
}
