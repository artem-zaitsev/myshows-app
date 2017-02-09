package com.nikart.util;

import android.content.SharedPreferences;

/**
 * Created by Artem
 */

public class PreferencesWorker {

    public static void saveSignedIn(SharedPreferences prefs, boolean value) {

        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean("SIGN IN", value);
        edit.apply();
    }

    public static boolean isSignedIn(SharedPreferences sharedPrefs) {
        return sharedPrefs.getBoolean("SIGN IN", false);
    }
}
