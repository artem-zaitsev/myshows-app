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

    private static SharedPreferences sharedPreferences;
    private static PreferencesWorker preferencesWorker;

    public static PreferencesWorker getInstance() {
        if (preferencesWorker == null) {
            preferencesWorker = new PreferencesWorker();
        }
        return preferencesWorker;
    }

    public void initSharedPreferences(SharedPreferences sharedPreferences) {
        PreferencesWorker.sharedPreferences = sharedPreferences;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void saveSignedIn(boolean value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(PREF_SIGN_IN, value);
        edit.apply();
    }

    public boolean isSignedIn() {
        return getSharedPreferences().getBoolean(PREF_SIGN_IN, false);
    }

    public void saveCookies(Set<String> cookies) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putStringSet(PREF_COOKIES, cookies);
        edit.apply();
    }

    public Set<String> getCookies() {
        return getSharedPreferences().getStringSet(PREF_COOKIES, new HashSet<String>());
    }
}
