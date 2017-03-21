package com.nikart.data.dto;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Artem
 */

@DatabaseTable(tableName = Episode.TABLE_NAME)
public class Episode implements Parcelable {

    public final static String TABLE_NAME = "episodes";
    public final static String FIELD_NAME_ID = "id";
    public final static String FIELD_NAME_TITLE = "title";
    public final static String FIELD_NAME_SHOW = "show";
    public final static String FIELD_NAME_SHORT_NAME = "short_name";
    public final static String FIELD_NAME_AIR_DATE = "air_date";
    public final static String FIELD_NAME_RATING = "rating";
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
    @DatabaseField(columnName = FIELD_NAME_ID, id = true)
    @SerializedName("episodeId")
    private int id;
    @DatabaseField(columnName = FIELD_NAME_TITLE)
    @SerializedName("title")
    private String title;
    @SerializedName("seasonNumber")
    private int seasonNumber;
    @SerializedName("episodeNumber")
    private int episodeNumber;
    @DatabaseField(columnName = FIELD_NAME_SHORT_NAME)
    private String shortName;
    @DatabaseField(columnName = FIELD_NAME_AIR_DATE)
    @SerializedName("airDate")
    private String airDate;
    @DatabaseField(columnName = FIELD_NAME_RATING)
    private int rate;
    @DatabaseField(columnName = FIELD_NAME_SHOW, foreign = true, foreignAutoRefresh = true)
    private Show show;
    @SerializedName("showId")
    private int showId;

    public Episode() {
    }

    protected Episode(Parcel in) {
        id = in.readInt();
        title = in.readString();
        shortName = in.readString();
        rate = in.readInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortName() {
        shortName = "s" + getSeasonNumber() + "e" + getEpisodeNumber();
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Date getAirDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date date = new Date();
        try {
            date = df.parse(airDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("DATE", toString() + date + " source: " + airDate);
        return date;
    }

    public void setAirDate(String airDate) {
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

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getShowId() {
        return showId;
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
