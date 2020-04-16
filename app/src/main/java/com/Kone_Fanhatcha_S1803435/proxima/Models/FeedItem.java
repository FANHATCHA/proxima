package com.Kone_Fanhatcha_S1803435.proxima.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public class FeedItem implements Parcelable {

    String title;
    String description;
    String link;
    String pubDate;
    String category;
    String geoLat;
    String geoLong;


    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel in) {
            return new FeedItem(in);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public String getGeoLong() {
        return geoLong;
    }

    public void setGeoLong(String geoLong) {
        this.geoLong = geoLong;
    }

    public FeedItem(String title, String description, String link, String pubDate, String category, String geoLat, String geoLong) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.category = category;
        this.geoLat = geoLat;
        this.geoLong = geoLong;
    }


    public FeedItem() {

    }

    protected FeedItem(Parcel in) {
        title = in.readString();
        description = in.readString();
        link = in.readString();
        pubDate = in.readString();
        category = in.readString();
        geoLat = in.readString();
        geoLong = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(link);
        parcel.writeString(pubDate);
        parcel.writeString(category);
        parcel.writeString(geoLat);
        parcel.writeString(geoLong);
    }
}
