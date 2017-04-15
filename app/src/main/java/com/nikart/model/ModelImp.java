package com.nikart.model;

import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.util.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 15.04.2017.
 */

public class ModelImp implements Model {

    @Override
    public Observable<List<Show>> getShows() {
        return App.getInstance().getApi().getShows()
                .map(
                        responseBody -> {
                            JsonParser<Show> parser = new JsonParser<>(responseBody);
                            List<Show> shows = null;
                            try {
                                shows = parser.getParsedList(Show.class);
                                HelperFactory.getHelper().getShowDAO().createInDataBase(shows);
                            } catch (IOException | JSONException | SQLException e) {
                                e.printStackTrace();
                            }
                            return shows;
                        }
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UserProfile> getUserInfo() {
        return App.getInstance().getApi().getUserProfile()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
