package com.nikart.util;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artem
 */

public class PreferencesWorker {

    public static final String PREF_SIGN_IN = "sign in";
    public static final String PREF_COOKIES = "cookies";
    public static SharedPreferences sharedPreferences;

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void saveSignedIn(boolean value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(PREF_SIGN_IN, value);
        edit.apply();
    }

    public static boolean isSignedIn() {
        return getSharedPreferences().getBoolean(PREF_SIGN_IN, false);
    }

    public static void saveCookies(Set<String> cookies) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putStringSet(PREF_COOKIES, cookies);
        edit.apply();
    }

    public static Set<String> getCookies() {
        return getSharedPreferences().getStringSet(PREF_COOKIES, new HashSet<String>());
    }
}
