package com.nikart.main;

/**
 * Created by Artem on 11.12.2016.
 */

public class Show {

    private String title;
    private int releaseDate, endDate;
    private String description;
    private String imageUrl;

    public Show() {
        this.title = "Sherlock";
        this.releaseDate = 2010;
        this.endDate = 2017;
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

    public Show(String title, int releaseDate, int endDate, String description, String imageUrl) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
