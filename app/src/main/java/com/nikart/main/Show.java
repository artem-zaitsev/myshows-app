package com.nikart.main;

/**
 * Created by Artem on 11.12.2016.
 */

public class Show {

    private int id;
    private String title;
    private String titleOriginal;
    private String description;
    private int totalSeasons;
    private String status;
    private String country;
    private String started, ended;
    private int year;
    private int watching;
    private int voted;
    private int rating;
    private String imageUrl;
    private String[] genresIds;
    private String[] episodes;

    public Show() {
        this.title = "Sherlock";
        this.titleOriginal = "Sherlock";
        this.started = Integer.toString(2010);
        this.ended = Integer.toString(2017);
        this.rating = 99;
        this.description =
                "\"<p>История о Шерлоке Холмсе и докторе Уотсоне в Лондоне начала " +
                        "двадцать первого века успела прославиться не только как одна из самых стильных " +
                        "и интригующих экранизаций рассказов сэра Артура Конан-Дойла, но и как шоу, " +
                        "создатели которого заставляют фанатов изнывать в ожидании новых серий не меньше " +
                        "пары-тройки мучительных лет. Ожидание, впрочем, того стоит. \\r\\n" +
                        "</p>\\r\\n<h3>В чем суть? </h3>\\r\\n" +
                        "<p>Хромающий афганский ветеран ищет соседа по квартире и по совету знакомого " +
                        "отправляется в морг к возможному кандидату. К счастью, живому – но с характером, " +
                        "являющим собой ядерный коктейль из социопатии, презрения к интеллектуальному " +
                        "уровню окружающих и прочих милых личностных качеств, включая неоспоримую " +
                        "гениальность. Лондон, тем временем, сотрясает череда необъяснимых убийств," +
                        " разобраться с которыми бравым служакам из Скотленд Ярда оказывается не по зубам. " +
                        "В дело под бодрый саундтрек вступают Шерлок и доктор Уотсон, вооруженные в " +
                        "придачу к давно знакомой дедукции еще и личным блогом, закодированными " +
                        "мобильниками и прочими благами цивилизации. \\r\\n</p>";
        this.imageUrl =
                "https://media.myshows.me/shows/normal/9/94/9492ce09d3a31c32ba559f5936dac888.jpg";
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
}
