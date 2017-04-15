package com.nikart.data.dto;

/**
 * Created by Artem on 11.03.2017.
 * Сгенерировал через сайт.
 * Ссылку кину позже.
 * Тестовая сущность. Можно доработать.
 * Берет информацию о пользователе из JSON'а.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("login")
    @Expose
    private Object login;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("wastedTime")
    @Expose
    private Object wastedTime;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("isPro")
    @Expose
    private Object isPro;
    @SerializedName("stats")
    @Expose
    private Stats stats;

    public Object getLogin() {
        return login;
    }

    public void setLogin(Object login) {
        this.login = login;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Object getWastedTime() {
        return wastedTime;
    }

    public void setWastedTime(Object wastedTime) {
        this.wastedTime = wastedTime;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getIsPro() {
        return isPro;
    }

    public void setIsPro(Object isPro) {
        this.isPro = isPro;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

}