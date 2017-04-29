package com.nikart.interactor.retrofit;

import com.nikart.model.dto.Show;
import com.nikart.model.dto.UserProfile;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Artem on 11.03.2017.
 * Апи для запросов.
 */

public interface MyShowsApi {

    // Берем информацию о пользователе без авторизации.
    @GET("profile/{login}")
    Observable<UserProfile> getAnyUserProfile(@Path("login") String login);

    //Авторизация
    @GET("profile/login")
    Observable<Response<ResponseBody>> signIn(@QueryMap Map<String, String> auth);

    //Авторизация через VK
    @GET("profile/login/vk")
    Observable<Response<ResponseBody>> singInVk(@QueryMap Map<String, String> auth);

    //Список сериалов
    @GET("profile/shows/")
    Observable<ResponseBody> getShows();

    //Информация об авторизированном пользователе
    @GET("profile/")
    Observable<UserProfile> getUserProfile();

    @GET("profile/episodes/next/")
    Observable<ResponseBody> getNextEpisodes();

    @GET("shows/{id}")
    Observable<Show> getShowById(@Path("id") int id);

    //Поиск сериала
    @GET("shows/search/")
    Observable<ResponseBody> findShowByName(@Query("q") String title);

    //Поставить рейтинг сериалу
    @GET("profile/shows/{id}/rate/{rate}")
    Observable<retrofit2.Response<ResponseBody>> updateShowRate(@Path("id") int id, @Path("rate") int rate);

}
