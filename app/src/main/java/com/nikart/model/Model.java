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

    Observable<List<Show>> getShows();

    Observable<Show> getShowById(int id);

    Observable<Boolean> signIn(String login, String password);

    Observable<List<List<Episode>>> getNextEpisodes();

    Observable<UserProfile> getUserInfo();

    Observable<Boolean> updateRateShow(int showId, int rate);

}
