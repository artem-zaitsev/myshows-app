package com.nikart.data.retrofit;

import com.nikart.data.dto.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Artem on 11.03.2017.
 * Апи для запросов.
 */

public interface MyShowsApi {

    //Тест на старом апи, берет информацию о пользователе без авторизации.
    @GET("profile/{login}")
    Call<UserProfile> getUserProfile(@Path("login") String login);
}
