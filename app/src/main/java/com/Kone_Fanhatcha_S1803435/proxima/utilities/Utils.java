package com.Kone_Fanhatcha_S1803435.proxima.utilities;

import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public class Utils {

    public Utils(){

    }

    /**
     *
     * @param title
     * @return magnitude
     */

    public String magnitude(String title){

        String[] splitRawTitle = title.split(":");

        String magnitude = "";

        for(int i=0; i<splitRawTitle.length; i++){

          magnitude = splitRawTitle[1];

        }


        return magnitude;

    }

    public String eventTitle(String title){

        String[] splitRawTitle = title.split(":");

        String eventTitle = "";

        for(int i=0; i<splitRawTitle.length; i++){

            eventTitle = splitRawTitle[2].substring(0, splitRawTitle[2].indexOf(","));

        }
        //
        return eventTitle;
    }

    public String eventDate(String pubDate){

        String[] rawPubDate = pubDate.split(",");
        String eventDate = "";
        for(int i=0; i<rawPubDate.length; i++){

            eventDate = rawPubDate[1].substring(0,12);

        }
        Log.i(eventDate, "PubDate");
        return eventDate;

    }
}
