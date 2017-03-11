package com.nikart.util;

import android.content.SharedPreferences;
import android.util.Log;

import com.nikart.app.App;

import java.io.IOException;
import java.util.HashSet;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 11.03.2017.
 * На основе статьи http://tsuharesu.com/handling-cookies-with-okhttp/
 */

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> fromPrefs = (HashSet<String>) PreferencesWorker.getCookies(App.getCookiesPrefs());
        for (String cookie : fromPrefs) {
            builder.addHeader("Cookie", cookie);
            Log.d("OkHTTP", "Cookie: " + cookie);
        }
        return chain.proceed(builder.build());
    }
}
