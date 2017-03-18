package com.nikart.interactor.interceptors;

import android.util.Log;

import com.nikart.util.PreferencesWorker;

import java.io.IOException;
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

        if (!origResponse.headers("Set-Cookie").isEmpty()) {
            List<String> headers = origResponse.headers("Set-Cookie");
            Log.d("OkHTTP", "Received cookie : " + headers);
            PreferencesWorker.getInstance().saveCookies(headers);
        }
        return origResponse;
    }
}
