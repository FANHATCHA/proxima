package com.Kone_Fanhatcha_S1803435.proxima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;


import com.Kone_Fanhatcha_S1803435.proxima.data.RssFeedReader;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Recent extends AppCompatActivity {

    private static final String TAG = "Recent";
    //UI components
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_location_on_black_24dp);

        mRecyclerView = findViewById(R.id.recyclerview);

        //Call Read rss asyncTask to fetch rss
        initRecyclerView();

        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.recent);

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
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.recent:
                        return true;
                }
                return false;
            }
        });

    }

    private void initRecyclerView(){
        RssFeedReader readRss = new RssFeedReader(this, mRecyclerView);
        readRss.execute();
    }


}
