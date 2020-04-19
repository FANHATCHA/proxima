package com.Kone_Fanhatcha_S1803435.proxima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Toast;

import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;
import com.Kone_Fanhatcha_S1803435.proxima.data.RssFeedReader;
import com.Kone_Fanhatcha_S1803435.proxima.data.SearchCallback;
import com.Kone_Fanhatcha_S1803435.proxima.utilities.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity implements SearchCallback {

    //Declare variable
    DatePicker periodPicker;
    Button sendButton;
    List<FeedItem> feedWithData = new ArrayList<>();
    Utils utils = new Utils();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        periodPicker =(DatePicker)findViewById(R.id.periodPicker);
        sendButton = (Button) findViewById(R.id.sendButton);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_location_on_black_24dp);

        initRecyclerView();

        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.search);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
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
    public void processFeedData(ArrayList<FeedItem> feedItems) {

        for(int i = 0 ; i < feedItems.size() ; i++) {
            feedWithData.add(feedItems.get(i));
        }
    }

    public void send(View v)
    {
        String dateSelected = periodPicker.getDayOfMonth()+"/"+ (periodPicker.getMonth() + 1)+"/"+periodPicker.getYear();
        System.out.println("Selected Date: "+ periodPicker.getDayOfMonth()+"/"+ (periodPicker.getMonth() + 1)+"/"+periodPicker.getYear());

        //check whether the period empty or not
        if(periodPicker.toString().length()>0) {
            Intent intent = new Intent(Search.this, SearchResult.class);
            intent.putExtra("largest_earthquake", (Serializable) utils.largestMagnEarthq(feedWithData));
            intent.putExtra("deepest_earthquake", (Serializable) utils.deepestEarthq(feedWithData));
            intent.putExtra("shallowest_earthquake", (Serializable) utils.shallowestEarthq(feedWithData));
            intent.putExtra("date_selected", dateSelected);
            startActivity(intent);

        } else {
            //display period if text field is empty
            Toast.makeText(getBaseContext(),"All fields are required",Toast.LENGTH_SHORT).show();
        }
    }
}
