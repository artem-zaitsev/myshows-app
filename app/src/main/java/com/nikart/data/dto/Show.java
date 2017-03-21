package com.nikart.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Artem
 */

@DatabaseTable(tableName = Show.TABLE_NAME)
public class Show {

    public final static String TABLE_NAME = "shows";
    public final static String FIELD_NAME_ID = "id";
    public final static String FIELD_NAME_TITLE = "title";
    public final static String FILED_NAME_TITLE_ORIG = "title_original";
    public final static String FIELD_NAME_DESCRIPTION = "description";
    public final static String FIELD_NAME_TOTAL_SEASON = "total_season";
    public final static String FIELD_NAME_STATUS = "status";
    public final static String FIELD_NAME_COUNTRY = "country";
    public final static String FIELD_NAME_STARTED = "started";
    public final static String FIELD_NAME_ENDED = "ended";
    public final static String FIELD_NAME_YEAR = "year";
    public final static String FIELD_NAME_WATCHING = "watching";
    public final static String FIELD_NAME_VOTED = "voted";
    public final static String FIELD_NAME_RATING = "rating";
    public final static String FIELD_NAME_IMAGE = "image_url";
    public final static String FIELD_NAME_EPISODES = "episodes"; // массив
    public final static String FIELD_NAME_WATCH_STATUS = "watch_status";
    public final static String FIELD_NAME_TOTAL_EPISODES = "total_episodes";
    public final static String FIELD_NAME_WATCHED_EPISODES = "watched_episodes";

    @DatabaseField(columnName = FIELD_NAME_ID, id = true)
    @SerializedName("showId")
    private int id;

    @DatabaseField(columnName = FIELD_NAME_TITLE)
    @SerializedName("ruTitle")
    private String title;

    @DatabaseField(columnName = FILED_NAME_TITLE_ORIG)
    @SerializedName("title")
    private String titleOriginal;

    @DatabaseField(columnName = FIELD_NAME_DESCRIPTION)
    @SerializedName("description")
    private String description;

    @DatabaseField(columnName = FIELD_NAME_TOTAL_SEASON)
    private int totalSeasons;

    @DatabaseField(columnName = FIELD_NAME_STATUS)
    @SerializedName("status")
    private String status;

    @DatabaseField(columnName = FIELD_NAME_COUNTRY)
    @SerializedName("country")
    private String country;

    @DatabaseField(columnName = FIELD_NAME_STARTED)
    @SerializedName("started")
    private String started;

    @DatabaseField(columnName = FIELD_NAME_ENDED)
    @SerializedName("ended")
    private String ended;

    @DatabaseField(columnName = FIELD_NAME_YEAR)
    @SerializedName("year")
    private int year;

    @DatabaseField(columnName = FIELD_NAME_WATCHING)
    private int watching;

    @DatabaseField(columnName = FIELD_NAME_VOTED)
    private int voted;

    @DatabaseField(columnName = FIELD_NAME_RATING)
    @SerializedName("rating")
    private float rating;

    @DatabaseField(columnName = FIELD_NAME_IMAGE)
    @SerializedName("image")
    private String imageUrl;

    @ForeignCollectionField(columnName = FIELD_NAME_EPISODES, eager = true)
    @Expose
    private ForeignCollection<Episode> episode;

    @DatabaseField(columnName = FIELD_NAME_WATCH_STATUS)
    @SerializedName("watchStatus")
    private String watchStatus;

    @DatabaseField(columnName = FIELD_NAME_TOTAL_EPISODES)
    @SerializedName("totalEpisodes")
    private int totalEpisodes;

    @DatabaseField(columnName = FIELD_NAME_WATCHED_EPISODES)
    @SerializedName("watchedEpisodes")
    private int watchedEpisodes;

    @SerializedName("genres")
    private String[] genresIds;

    @SerializedName("runtime")
    private Long runtime;

    public Show() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOriginal() {
        return titleOriginal;
    }

    public void setTitleOriginal(String titleOriginal) {
        this.titleOriginal = titleOriginal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(int totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWatching() {
        return watching;
    }

    public void setWatching(int watching) {
        this.watching = watching;
    }

    public int getVoted() {
        return voted;
    }

    public void setVoted(int voted) {
        this.voted = voted;
    }

    public int getRating() {
        return Math.round(rating);
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWatchStatus() {
        return watchStatus;
    }

    public void setWatchStatus(String watchStatus) {
        this.watchStatus = watchStatus;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public int getWatchedEpisodes() {
        return watchedEpisodes;
    }

    public void setWatchedEpisodes(int watchedEpisodes) {
        this.watchedEpisodes = watchedEpisodes;
    }

    public int getUnwatchedEpisodes() {
        return totalEpisodes - watchedEpisodes;
    }

    public ForeignCollection<Episode> getEpisodes() {
        return episode;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }
}
