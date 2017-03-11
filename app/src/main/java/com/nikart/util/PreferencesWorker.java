package com.nikart.util;

import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artem
 */

public class PreferencesWorker {

    public static final String PREF_SIGN_IN = "sign in";
    public static final String PREF_COOKIES = "cookies";

    public static void saveSignedIn(SharedPreferences prefs, boolean value) {

        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(PREF_SIGN_IN, value);
        edit.apply();
    }

    public static boolean isSignedIn(SharedPreferences sharedPrefs) {
        return sharedPrefs.getBoolean(PREF_SIGN_IN, false);
    }

    public static void saveCookies(SharedPreferences prefs, Set<String> cookies) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putStringSet(PREF_COOKIES, cookies);
        edit.apply();
    }

    public static Set<String> getCookies(SharedPreferences sharedPrefs) {
        return sharedPrefs.getStringSet(PREF_COOKIES, new HashSet<String>());
    }
}
