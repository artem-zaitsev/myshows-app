package com.nikart.model.api;

import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.interactor.ApiManager;
import com.nikart.model.Model;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 15.04.2017.
 * Implementation of Model
 */

public class ApiModel implements Model {

    @Override
    public Observable<List<Show>> getShows() {
        return ApiManager.getInstance().getShows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Show> getShowById(int id) {
        return ApiManager.getInstance().getShowById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Boolean> signIn(String login, String password) {
        return ApiManager.getInstance().signIn(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<List<Episode>>> getNextEpisodes() {
        return ApiManager.getInstance().getNextEpisodes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UserProfile> getUserInfo() {
        return ApiManager.getInstance().getUserProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Boolean> updateRateShow(int showId, int rate) {
        return ApiManager.getInstance().updateRateShow(showId, rate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
