package com.nikart.model;

import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;

import java.util.List;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by Artem on 15.04.2017.
 * Model interface.
 */

public interface Model {

    public Observable<List<Show>> getShows();
    public Observable<UserProfile> getUserInfo();
    public Observable<Response<ResponseBody>> updateRateShow(int showId, int rate);

}
