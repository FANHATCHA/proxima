package com.Kone_Fanhatcha_S1803435.proxima;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;
import com.Kone_Fanhatcha_S1803435.proxima.utilities.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class FeedDetails extends AppCompatActivity implements OnMapReadyCallback {

     TextView titleText;
     GoogleMap gMap;
     Double lat;
     Double lon;
     String magnitude;

     String date;

     String title;
     TextView pubDateText;
     TextView magnitudeText;
     TextView categoryText;
     TextView depthText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);
        //Action bar | toolbar | logo icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_location_on_black_24dp);

        titleText = findViewById(R.id.titleText);
        pubDateText = findViewById(R.id.pubDateText);
        magnitudeText = findViewById(R.id.magnitudeText);
        categoryText = findViewById(R.id.categoryText);
        Button link = findViewById(R.id.link);
        depthText = findViewById(R.id.depthText);


        Intent intent = getIntent();


        final FeedItem feedItem = intent.getParcelableExtra("activity_feed_details");

        lat = Double.valueOf(feedItem.getGeoLat());
        lon = Double.valueOf(feedItem.getGeoLong());

        Utils utils = new Utils();
        magnitude = utils.magnitude(feedItem.getTitle());

        title = utils.eventTitle(feedItem.getTitle());

        date = utils.eventDate(feedItem.getPubDate());


          titleText.setText(title);
          pubDateText.setText(date);
          magnitudeText.setText(magnitude);
          categoryText.setText("Category: " + feedItem.getCategory());
          depthText.setText(utils.eventDepth(feedItem.getDescription()));
          link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = feedItem.getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        //Obtain the SupportMapFragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap){

        gMap = googleMap;

        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                latLng = new LatLng(lat,lon);

                //creating markers
                MarkerOptions markerOptions = new MarkerOptions();

                //set marker position
                markerOptions.position(latLng);

                //set Latitude and Longitude
                markerOptions.title(title + " : " + magnitude);

                //Clear the previously clicked position
                gMap.clear();

                //Zoom the marker
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                //add marker on map
                gMap.addMarker(markerOptions);
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
