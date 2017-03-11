package com.nikart.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.nikart.data.HelperFactory;
import com.nikart.data.retrofit.MyShowsApi;
import com.nikart.util.PreferencesWorker;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Artem on 07.03.2017.
 */

public class App extends Application {

    private static MyShowsApi api; // ссылка на реализацию апи
    private static SharedPreferences cookiesPrefs; // для хранения куков
    private static SharedPreferences signInPrefs; // для хранения флага логина

    public static MyShowsApi getApi() {
        return api;
    }

    public static SharedPreferences getCookiesPrefs() {
        return cookiesPrefs;
    }

    public static SharedPreferences getSignInPrefs() {
        return signInPrefs;
    }

    /*Надо вынести все библиотеки в отдельные методы*/
    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myshows.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(MyShowsApi.class);
        cookiesPrefs = getSharedPreferences(PreferencesWorker.PREF_COOKIES, MODE_PRIVATE);
        signInPrefs = getSharedPreferences(PreferencesWorker.PREF_SIGN_IN, MODE_PRIVATE);
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
