package com.nikart.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.nikart.data.HelperFactory;
import com.nikart.interactor.interceptors.AddCookiesInterceptor;
import com.nikart.interactor.interceptors.ReceivedCookieInterceptor;
import com.nikart.interactor.retrofit.MyShowsApi;
import com.nikart.util.PreferencesWorker;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Artem on 07.03.2017.
 */

public class App extends Application {

    private static App appInstance;
    public final String BASE_URL = "https://api.myshows.me/";

    private MyShowsApi api; // ссылка на реализацию апи
    private OkHttpClient client; // ссылка на клиент
    private Retrofit retrofit;// ретрофит

    public static App getInstance() {
        return appInstance;
    }

    public MyShowsApi getApi() {
        return api;
    }

    public OkHttpClient getClient() {
        return client;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        HelperFactory.setHelper(getApplicationContext());
        initClient();
        initRetrofit();
        initApi();
        initPreferences();
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }

    private void initClient() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookieInterceptor())
                .build();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void initApi() {
        api = retrofit.create(MyShowsApi.class);
    }

    private void initPreferences() {
        //Инициализируем статическую sharedPreferences в PrefWorker
        PreferencesWorker.sharedPreferences =
                getSharedPreferences(PreferencesWorker.PREF_SIGN_IN, MODE_PRIVATE);
    }
}
