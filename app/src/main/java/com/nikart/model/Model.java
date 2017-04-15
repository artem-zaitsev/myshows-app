package com.nikart.model;

import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Artem on 15.04.2017.
 */

public interface Model {

    public Observable<List<Show>> getShows();
    public Observable<UserProfile> getUserInfo();
}
