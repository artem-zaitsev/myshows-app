package com.nikart.util;

import android.content.SharedPreferences;

import com.nikart.app.App;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Artem on 11.03.2017.
 * На основе статьи http://tsuharesu.com/handling-cookies-with-okhttp/
 */

public class ReceivedCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response origResponse = chain.proceed(chain.request());

        if (!origResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : origResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            PreferencesWorker.saveCookies(App.getCookiesPrefs(), cookies);
        }
        return origResponse;
    }
}
