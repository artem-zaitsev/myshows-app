package com.nikart.main;

import java.util.Date;

/**
 * Created by Artem
 */

class Episode {
    private String title, season, show_title;
    private Date start_date;

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
        this.start_date = new Date(10102016);
        int random1 = (int)(Math.random()*12 / 4);
        int random2 = (int)(Math.random()*12 / 4);
        this.title = titles[random1];
        this.show_title = shows[random2];
    }

    public Episode(String title, String season, String show_title, Date start_date) {
        this.title = title;
        this.season = season;
        this.show_title = show_title;
        this.start_date = start_date;
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
        return show_title;
    }

    public void setShowTitle(String show_title) {
        this.show_title = show_title;
    }

    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }
}
