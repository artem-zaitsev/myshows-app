package com.nikart.dto;

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

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_TITLE)
    private String title;

    @DatabaseField(columnName = FILED_NAME_TITLE_ORIG)
    private String titleOriginal;

    @DatabaseField(columnName = FIELD_NAME_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = FIELD_NAME_TOTAL_SEASON)
    private int totalSeasons;

    @DatabaseField(columnName = FIELD_NAME_STATUS)
    private String status;

    @DatabaseField(columnName = FIELD_NAME_COUNTRY)
    private String country;

    @DatabaseField(columnName = FIELD_NAME_STARTED)
    private String started;

    @DatabaseField(columnName = FIELD_NAME_ENDED)
    private String ended;

    @DatabaseField(columnName = FIELD_NAME_YEAR)
    private int year;

    @DatabaseField(columnName = FIELD_NAME_WATCHING)
    private int watching;

    @DatabaseField(columnName = FIELD_NAME_VOTED)
    private int voted;

    @DatabaseField(columnName = FIELD_NAME_RATING)
    private int rating;

    @DatabaseField(columnName = FIELD_NAME_IMAGE)
    private String imageUrl;

    @ForeignCollectionField(columnName = FIELD_NAME_EPISODES, eager = true)
    private ForeignCollection<Episode> episodes;

    @DatabaseField(columnName = FIELD_NAME_WATCH_STATUS)
    private String watchStatus;

    @DatabaseField(columnName = FIELD_NAME_TOTAL_EPISODES)
    private int totalEpisodes;

    @DatabaseField(columnName = FIELD_NAME_WATCHED_EPISODES)
    private int watchedEpisodes;

    private String[] genresIds;

    public Show() {
        this.title = "Sherlock";
        this.titleOriginal = "Sherlock";
        this.started = Integer.toString(2010);
        this.ended = Integer.toString(2017);
        this.rating = 0;
        this.description =
                "\n<p>История о Шерлоке Холмсе и докторе Уотсоне в Лондоне начала " +
                        "двадцать первого века успела прославиться не только как одна из самых стильных " +
                        "и интригующих экранизаций рассказов сэра Артура Конан-Дойла, но и как шоу, " +
                        "создатели которого заставляют фанатов изнывать в ожидании новых серий не меньше " +
                        "пары-тройки мучительных лет. Ожидание, впрочем, того стоит." + "\r\n" +
                        "</p>" + "\r\n" + "<h3>В чем суть?</h3>" + "\r\n" +
                        "<p>Хромающий афганский ветеран ищет соседа по квартире и по совету знакомого " +
                        "отправляется в морг к возможному кандидату. К счастью, живому – но с характером, " +
                        "являющим собой ядерный коктейль из социопатии, презрения к интеллектуальному " +
                        "уровню окружающих и прочих милых личностных качеств, включая неоспоримую " +
                        "гениальность. Лондон, тем временем, сотрясает череда необъяснимых убийств," +
                        " разобраться с которыми бравым служакам из Скотленд Ярда оказывается не по зубам. " +
                        "В дело под бодрый саундтрек вступают Шерлок и доктор Уотсон, вооруженные в " +
                        "придачу к давно знакомой дедукции еще и личным блогом, закодированными " +
                        "мобильниками и прочими благами цивилизации." + "\r\n" + "</p>";
        this.imageUrl =
                "https://media.myshows.me/shows/normal/9/94/9492ce09d3a31c32ba559f5936dac888.jpg";
        this.totalEpisodes = 12;
        this.watchedEpisodes = 3;
    }

    public Show(String title, int started, int ended, String description, String imageUrl) {
        this.title = title;
        this.started = Integer.toString(started);
        this.ended = Integer.toString(ended);
        this.description = description;
        this.imageUrl = imageUrl;
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
        return rating;
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
        return episodes;
    }
}
