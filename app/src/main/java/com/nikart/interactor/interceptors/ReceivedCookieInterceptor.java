package com.nikart.interactor.interceptors;

import android.util.Log;

import com.nikart.util.PreferencesWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<String> savedHeaders = PreferencesWorker.getInstance().getCookies();
        List<String> cookies = new ArrayList<>();
        if (!origResponse.headers("Set-Cookie").isEmpty()
                && origResponse.headers("Set-Cookie").get(0).contains("PHPSESID")) {
            List<String> headers = origResponse.headers("Set-Cookie");
            cookies.addAll(headers);
            if (headers.size() == 1) {
                cookies.add(savedHeaders.get(0));
                cookies.add(savedHeaders.get(1));
            }

            Log.d("OkHTTP", "Received cookie : " + headers);
            Log.d("OkHTTP", "Saved cookie : " + savedHeaders);
            Log.d("OkHTTP", "Saving cookie : " + cookies);
            PreferencesWorker.getInstance().saveCookies(cookies);
        }
        return origResponse;
    }
}
