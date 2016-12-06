package com.nikart.main;

import java.util.Date;

/**
 * Created by Artem
 */

class Episode {
    private String title, season, showTitle;
    private Date startDate;

    // Конструктор с сгенерированными эпизодами.
    Episode() {
        String[] titles = new String[]{
                "Promo",
                "Something new",
                "Knock-knock",
                "Hello, World!"
        };
        String[] shows = new String[] {
                "Sherlock",
                "Lost",
                "The Big Bang",
                "?????"
        };
        this.season = "1";
        this.startDate = new Date(10102016);
        int random1 = (int)(Math.random()*12 / 4);
        int random2 = (int)(Math.random()*12 / 4);
        this.title = titles[random1];
        this.showTitle = shows[random2];
    }

    public Episode(String title, String season, String showTitle, Date startDate) {
        this.title = title;
        this.season = season;
        this.showTitle = showTitle;
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
