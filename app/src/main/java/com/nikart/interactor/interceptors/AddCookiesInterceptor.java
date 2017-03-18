package com.nikart.interactor.interceptors;

import android.util.Log;

import com.nikart.util.PreferencesWorker;

import java.io.IOException;
import java.util.HashSet;

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

        HashSet<String> fromPrefs = (HashSet<String>) PreferencesWorker.getInstance().getCookies();
        for (String cookie : fromPrefs) {
            builder.addHeader("Cookie", cookie);
            Log.d("OkHTTP", "Add Cookie: " + cookie);
        }
        Log.d("OkHTTP", "Add cookie from prefs " + PreferencesWorker.getInstance().getCookies());
        return chain.proceed(builder.build());
    }
}
