package com.nikart.main;

import java.util.Date;

/**
 * Created by Artem
 */

public class Episode {
    private String title, showTitle;
    private int seasonNumber;
    private Date airDate;

    // Конструктор с сгенерированными эпизодами.
    public Episode() {
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
        this.seasonNumber = 1;
        this.airDate = new Date(10102016);
        int random1 = (int) (Math.random() * 12 / 4);
        int random2 = (int) (Math.random() * 12 / 4);
        this.title = titles[random1];
        this.showTitle = shows[random2];
    }

    public Episode(String title, int seasonNumber, String showTitle, Date airDate) {
        this.title = title;
        this.seasonNumber = seasonNumber;
        this.showTitle = showTitle;
        this.airDate = airDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
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
}
