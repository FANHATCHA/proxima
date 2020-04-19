package com.Kone_Fanhatcha_S1803435.proxima.utilities;

import android.util.Log;
import android.widget.ListView;

import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;
import com.Kone_Fanhatcha_S1803435.proxima.data.RssFeedReader;
import com.Kone_Fanhatcha_S1803435.proxima.data.SearchCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public class Utils{

    List<String>feedWithData;

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

    public String eventDepth(String description){

        String[] rawDescription = description.split(";");
        String eventDepth = "";
        for(int i=0; i<rawDescription.length; i++){

            eventDepth = rawDescription[3];

        }
        return eventDepth;
    }




    public Map<String, Double> largestMagnEarthq(List<FeedItem> rawFeedData){

        String rawMagnitude;
        HashMap<String, Double> rawHashMap = new HashMap<>();

        HashMap<String, Double> finalMap = new HashMap<>();


       for(int i=0; i<rawFeedData.size(); i++){
           rawMagnitude =  magnitude(rawFeedData.get(i).getTitle()).substring(2,6);
           rawHashMap.put(eventTitle(rawFeedData.get(i).getTitle()),Double.valueOf(rawMagnitude));

       }

        Map.Entry<String, Double> maxEntry = null;
        for (Map.Entry<String, Double> entry : rawHashMap.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;

            }
        }
        finalMap.put( maxEntry.getKey(), maxEntry.getValue());
        System.out.println("Key of hashset" + " " +  maxEntry.getKey());
        System.out.println("Value of hashset" + " " +  maxEntry.getValue());
       return finalMap;

    }


    public Map<String, Integer> deepestEarthq(List<FeedItem> rawFeedData){

        String rawDeepest;
        HashMap<String, Integer> rawHashMap = new HashMap<>();

        HashMap<String, Integer> finalMap = new HashMap<>();


        for(int i=0; i<rawFeedData.size(); i++){
            rawDeepest = eventDepth(rawFeedData.get(i).getDescription());
            String[] rawDeepestArr = rawDeepest.split(" ");

            System.out.println("Raw deepest " + " " + rawDeepestArr[2]);
            rawHashMap.put(eventTitle(rawFeedData.get(i).getTitle()),Integer.valueOf(rawDeepestArr[2]));

        }

        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : rawHashMap.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;

            }
        }
        finalMap.put( maxEntry.getKey(), maxEntry.getValue());
        System.out.println("Key of hashset deepest" + " " +  maxEntry.getKey());
        System.out.println("Value of hashset deepest" + " " +  maxEntry.getValue());
        return finalMap;

    }

    public Map<String, Integer> shallowestEarthq(List<FeedItem> rawFeedData){

        String rawshallowest;
        HashMap<String, Integer> rawHashMap = new HashMap<>();

        HashMap<String, Integer> finalMap = new HashMap<>();


        for(int i=0; i<rawFeedData.size(); i++){
            rawshallowest = eventDepth(rawFeedData.get(i).getDescription());
            String[] rawshallowestArr = rawshallowest.split(" ");

            System.out.println("Raw shallowest " + " " + rawshallowestArr[2]);
            rawHashMap.put(eventTitle(rawFeedData.get(i).getTitle()),Integer.valueOf(rawshallowestArr[2]));

        }

        Map.Entry<String, Integer> minEntry = null;
        for (Map.Entry<String, Integer> entry : rawHashMap.entrySet())
        {
            if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0)
            {
                minEntry = entry;

            }
        }
        finalMap.put( minEntry.getKey(), minEntry.getValue());
        System.out.println("Key of hashset shallowest" + " " +  minEntry.getKey());
        System.out.println("Value of hashset shallowest" + " " +  minEntry.getValue());
        return finalMap;

    }


}
