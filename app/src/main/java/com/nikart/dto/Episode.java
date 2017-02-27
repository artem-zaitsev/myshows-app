package com.nikart.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Artem
 */

public class Episode implements Parcelable{

    private int id;
    private String title, showTitle;
    private String shortName;
    private Date airDate;
    private int rate;


    // Конструктор с сгенерированными эпизодами.
    public Episode(int i) {
        String[] titles = new String[]{
                "Promo",
                "Something new",
                "Knock-knock",
                "Hello, World!"
        };
        String[] shows = new String[]{
                "Sherlock",
                "Lost",
                "The Big Bang",
                "?????"
        };
        this.shortName = "s" + i +"e1";
        this.airDate = new Date(2017,05,17);
        int random1 = (int) (Math.random() * 12 / 4);
        int random2 = (int) (Math.random() * 12 / 4);
        this.title = titles[random1];
        this.showTitle = shows[random2];
        this.rate = 0;
    }

    public Episode(String title, String shortName, String showTitle, Date airDate) {
        this.title = title;
        this.shortName = shortName;
        this.showTitle = showTitle;
        this.airDate = airDate;
    }

    protected Episode(Parcel in) {
        id = in.readInt();
        title = in.readString();
        showTitle = in.readString();
        shortName= in.readString();
        rate = in.readInt();
    }

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public Date getAirDate() {
        return airDate;
    }

    public void setAirDate(Date airDate) {
        this.airDate = airDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(showTitle);
        parcel.writeString(shortName);
        parcel.writeInt(rate);
    }
}
