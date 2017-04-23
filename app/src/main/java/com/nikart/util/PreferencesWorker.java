package com.nikart.util;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem
 */

public class PreferencesWorker {

    public static final String PREFERENCES = "myshows.prefs";
    public static final String PREF_SIGN_IN = "sign in";
    public static final String PREF_COOKIES = "cookies";
    private static  final String PREF_LOGIN  = "login";
    private static  final String PREF_PASSWORD = "password";
    private static final String COOKIES_SESID = "PHPSESID";
    private static final String COOKIES_LOGIN = "cookies_login";
    private static final String COOKIES_PASS = "cookies_password";

    private static SharedPreferences sharedPreferences;
    private static PreferencesWorker preferencesWorker;
    private SharedPreferences.Editor edit;

    public static PreferencesWorker getInstance() {
        if (preferencesWorker == null) {
            preferencesWorker = new PreferencesWorker();
        }
        return preferencesWorker;
    }

    public void initSharedPreferences(SharedPreferences sharedPreferences) {
        PreferencesWorker.sharedPreferences = sharedPreferences;
        edit = sharedPreferences.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void saveSignedIn(boolean value) {
        edit.putBoolean(PREF_SIGN_IN, value);
        edit.apply();
    }

    public boolean isSignedIn() {
        return getSharedPreferences().getBoolean(PREF_SIGN_IN, false);
    }

    public void saveLogin(String login) {
        edit.putString(PREF_LOGIN, login).apply();
    }

    public String getLogin() {
        return  getSharedPreferences().getString(PREF_LOGIN, null);
    }

    public void savePassword(String password) {
        edit.putString(PREF_PASSWORD, password).apply();
    }

    public String getPassword() {
        return getSharedPreferences().getString(PREF_PASSWORD, null);
    }

    public void saveCookies(List<String> cookies) {
        edit.putString(COOKIES_SESID, cookies.get(0))
                .putString(COOKIES_LOGIN, cookies.get(1))
                .putString(COOKIES_PASS, cookies.get(2))
                .apply();
    }

    public void clearCookies() {
        edit.putString(COOKIES_SESID, "")
                .putString(COOKIES_LOGIN, "")
                .putString(COOKIES_PASS, "")
                .apply();
    }

    public List<String> getCookies() {
        List<String> cookies = new ArrayList<>();
        cookies.add(getSharedPreferences().getString(COOKIES_LOGIN, null));
        cookies.add(getSharedPreferences().getString(COOKIES_PASS, null));
        cookies.add(getSharedPreferences().getString(COOKIES_SESID, null));
        return cookies;
    }
}
