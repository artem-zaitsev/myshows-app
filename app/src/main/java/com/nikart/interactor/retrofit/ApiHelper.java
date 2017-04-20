package com.nikart.interactor.retrofit;

import com.nikart.interactor.interceptors.AddCookiesInterceptor;
import com.nikart.interactor.interceptors.ReceivedCookieInterceptor;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Artem on 18.04.2017.
 */


public class ApiHelper {

    private static ApiHelper helper;
    public final String BASE_URL = "https://api.myshows.me/";
    private MyShowsApi api;


    public static ApiHelper getInstance() {
        if (helper == null) helper = new ApiHelper();
        return helper;
    }

    public void initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookieInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(MyShowsApi.class);
    }

    public MyShowsApi getApi() {
        return api;
    }
}
