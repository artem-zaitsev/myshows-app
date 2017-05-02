package com.nikart.model;

import com.nikart.model.dto.Episode;
import com.nikart.model.dto.Show;
import com.nikart.model.dto.ShowTmp;
import com.nikart.model.dto.UserProfile;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Artem on 15.04.2017.
 * Model interface.
 */

public interface Model {

    Observable<Boolean> signIn(String login, String password);

    Observable<Boolean> signInVk(String token, String userId);

    Observable<List<Show>> getShows();

    Observable<Show> getShowById(int id);

    Observable<List<ShowTmp>> findShowByName(String title);

    Observable<List<List<Episode>>> getNextEpisodes();

    Observable<UserProfile> getUserInfo();

    Observable<Boolean> updateRateShow(int showId, int rate);

}
