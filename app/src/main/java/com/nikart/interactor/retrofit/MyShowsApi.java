package com.nikart.interactor.retrofit;

import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Artem on 11.03.2017.
 * Апи для запросов.
 */

public interface MyShowsApi {

    // Берем информацию о пользователе без авторизации.
    @GET("profile/{login}")
    Observable<UserProfile> getAnyUserProfile(@Path("login") String login);

    //Список сериалов
    /*@GET("profile/shows/")
    Call<ResponseBody> getShows();*/
    @GET("profile/shows/")
    Observable<ResponseBody> getShows();

    //Информация об авторизированном пользователе
    /*@GET("profile/")
    Call<UserProfile> getUserProfile();*/
    @GET("profile/")
    Observable<UserProfile> getUserProfile();

    @GET("profile/episodes/next/")
    Call<ResponseBody> getNextEpisodes();

    @GET("shows/{id}")
    Call<Show> getShowById(@Path("id") int id);

    @GET("profile/shows/{id}/rate/{rate}")
    Call<ResponseBody> updateShowRate(@Path("id") int id, @Path("rate") int rate);

}
