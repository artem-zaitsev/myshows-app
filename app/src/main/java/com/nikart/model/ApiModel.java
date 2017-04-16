package com.nikart.model;

import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.interactor.ApiManager;

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
