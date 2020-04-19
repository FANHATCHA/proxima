package com.Kone_Fanhatcha_S1803435.proxima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;


import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;
import com.Kone_Fanhatcha_S1803435.proxima.data.Callback;

import com.Kone_Fanhatcha_S1803435.proxima.data.RssFeedReader;
import com.Kone_Fanhatcha_S1803435.proxima.utilities.Utils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, Callback {

    //Declaration of variable
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Action bar | toolbar | logo icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_location_on_black_24dp);


        initRecyclerView();

        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Obtain the SupportMapFragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.multiple_markers);

        supportMapFragment.getMapAsync(this);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),
                                Search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.recent:
                        startActivity(new Intent(getApplicationContext(),
                                Recent.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void initRecyclerView() {
        RssFeedReader readRss = new RssFeedReader(this, this);
        readRss.execute();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
    }

    @Override
    public void processData(ArrayList<FeedItem> feedItems) {
        Utils utils = new Utils();
        for(int i = 0 ; i < feedItems.size() ; i++) {
            gMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(feedItems.get(i).getGeoLat()), Double.parseDouble(feedItems.get(i).getGeoLong())))
                    .anchor(0.5f, 0.5f)
                    .title(utils.eventTitle(feedItems.get(i).getTitle()) + " : " + utils.magnitude(feedItems.get(i).getTitle()))
                    .snippet(utils.eventDepth(feedItems.get(i).getDescription()))
                    );
        }
    }
}

