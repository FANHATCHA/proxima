package com.Kone_Fanhatcha_S1803435.proxima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


public class FeedDetails extends AppCompatActivity {

    //Declaration of variable
     TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);

        titleText = findViewById(R.id.titleText);

        Intent intent = getIntent();

        titleText.setText(intent.getStringExtra("titleText"));
    }
}
