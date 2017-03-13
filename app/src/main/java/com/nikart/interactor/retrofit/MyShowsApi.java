package com.nikart.interactor.retrofit;

import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.interactor.Answer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Artem on 11.03.2017.
 * Апи для запросов.
 */

public interface MyShowsApi {

    // Берем информацию о пользователе без авторизации.
    @GET("profile/{login}")
    Call<UserProfile> getUserProfile(@Path("login") String login);

    //Список сериалов
    @GET("profile/shows")
    Call<List<Show>> getShows();
}
