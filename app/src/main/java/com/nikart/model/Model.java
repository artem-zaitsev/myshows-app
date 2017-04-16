package com.nikart.model;

import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Artem on 15.04.2017.
 * Model interface.
 */

public interface Model {

    public Observable<List<Show>> getShows();

    public Observable<List<Episode>> getNextEpisodes();

    public Observable<UserProfile> getUserInfo();

    public Observable<Boolean> updateRateShow(int showId, int rate);

}
