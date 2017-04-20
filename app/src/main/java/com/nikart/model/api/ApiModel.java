package com.nikart.model.api;

import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.interactor.ApiRepository;
import com.nikart.model.DaggerModelComponent;
import com.nikart.model.Model;
import com.nikart.model.ModelComponent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 15.04.2017.
 * Implementation of Model
 */

public class ApiModel implements Model {

    private static ApiModel apiModel;
    private static ModelComponent component = DaggerModelComponent.create();

    public static ApiModel getInstance() {
        if (apiModel == null) apiModel = new ApiModel();
        return apiModel;
    }

    public static ModelComponent getComponent() {
        return component;
    }

    @Override
    public Observable<List<Show>> getShows() {
        return component.getApiRepository().getShows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Show> getShowById(int id) {
        return component.getApiRepository().getShowById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Boolean> signIn(String login, String password) {
        return component.getApiRepository().signIn(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<List<Episode>>> getNextEpisodes() {
        return component.getApiRepository().getNextEpisodes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UserProfile> getUserInfo() {
        return component.getApiRepository().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Boolean> updateRateShow(int showId, int rate) {
        return component.getApiRepository().updateRateShow(showId, rate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
