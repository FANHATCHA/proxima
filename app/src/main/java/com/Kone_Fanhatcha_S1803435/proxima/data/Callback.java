package com.Kone_Fanhatcha_S1803435.proxima.data;

import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;

import java.util.ArrayList;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public interface Callback {
    void processData(ArrayList<FeedItem> feedItems );
}
