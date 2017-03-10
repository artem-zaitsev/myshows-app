package com.nikart.data.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Artem
 */

@DatabaseTable(tableName = Episode.TABLE_NAME)
public class Episode implements Parcelable{

    public final static String TABLE_NAME = "episodes";
    public final static String FIELD_NAME_ID = "id";
    public final static String FIELD_NAME_TITLE = "title";
    public final static String FIELD_NAME_SHOW = "show";
    public final static String FIELD_NAME_SHORT_NAME = "short_name";
    public final static String FIELD_NAME_AIR_DATE = "air_date";
    public final static String FIELD_NAME_RATING = "rating";

    @DatabaseField(columnName = FIELD_NAME_ID, id = true)
    private int id;
    @DatabaseField(columnName = FIELD_NAME_TITLE)
    private String title;
    @DatabaseField(columnName = FIELD_NAME_SHORT_NAME)
    private String shortName;
    @DatabaseField(columnName = FIELD_NAME_AIR_DATE)
    private Date airDate;
    @DatabaseField(columnName = FIELD_NAME_RATING)
    private int rate;

    @DatabaseField(columnName = FIELD_NAME_SHOW, foreign = true, foreignAutoRefresh = true)
    private Show show;


    public Episode(){
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
        this.shortName = "s1e1";
        this.airDate = new Date(2017,5,17);
        int random1 = (int) (Math.random() * 12 / 4);
        int random2 = (int) (Math.random() * 12 / 4);
        this.title = titles[random1];
        this.rate = 0;
    }
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
        this.rate = 0;
    }

    public Episode(String title, String shortName, String showTitle, Date airDate) {
        this.title = title;
        this.shortName = shortName;
        this.airDate = airDate;
    }

    protected Episode(Parcel in) {
        id = in.readInt();
        title = in.readString();
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


    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(shortName);
        parcel.writeInt(rate);
    }
}
