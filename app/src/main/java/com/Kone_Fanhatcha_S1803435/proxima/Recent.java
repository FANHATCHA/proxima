package com.Kone_Fanhatcha_S1803435.proxima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;

import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;
import com.Kone_Fanhatcha_S1803435.proxima.adapters.FeedsAdapter;
import com.Kone_Fanhatcha_S1803435.proxima.data.RssFeedReader;
import com.Kone_Fanhatcha_S1803435.proxima.utilities.VerticalSpacingItemDecorator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Recent extends AppCompatActivity implements FeedsAdapter.OnRssFeedListener {

    private static final String TAG = "Recent";
    //UI components
    private RecyclerView mRecyclerView;

    // vars
    private ArrayList<FeedItem> mFeedItems = new ArrayList<>();
    private FeedsAdapter mFeedsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_location_on_black_24dp);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //Call Read rss asyncTask to fetch rss
        initRecyclerView();
//        RssFeedReader readRss = new RssFeedReader(this, mRecyclerView);
//        readRss.execute();
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
        
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mFeedsAdapter = new FeedsAdapter(mFeedItems, this);
        mRecyclerView.setAdapter(mFeedsAdapter);
    }


    public void onRssFeedClicked(int position) {
        Intent intent = new Intent(this, FeedDetails.class);
        intent.putExtra("selected_rss_feed", mFeedItems.get(position));
        startActivity(intent);
    }

}
