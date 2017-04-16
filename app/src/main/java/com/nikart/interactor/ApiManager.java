package com.nikart.interactor;

import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.interactor.retrofit.MyShowsApi;
import com.nikart.util.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Artem on 16.04.2017.
 * Manager for API api.myshows.me.
 */

public class ApiManager {

    private static ApiManager apiManager;
    private MyShowsApi api = App.getInstance().getApi();

    public static ApiManager getInstance() {
        if (apiManager == null) apiManager = new ApiManager();
        return apiManager;
    }

    public Observable<List<Show>> getShows() {
        return api.getShows()
                .map(responseBody -> {
                    JsonParser<Show> parser = new JsonParser<>(responseBody);
                    List<Show> shows = null;
                    try {
                        shows = parser.getParsedList(Show.class);
                        HelperFactory.getHelper().getShowDAO().createInDataBase(shows);
                    } catch (IOException | JSONException | SQLException e) {
                        e.printStackTrace();
                    }
                    return shows;
                });
    }

    public Observable<UserProfile> getUserProfile() {
        return App.getInstance().getApi().getUserProfile();
    }

    public Observable<Boolean> updateRateShow(int showId, int rate) {
        return App.getInstance().getApi().updateShowRate(showId, rate)
                .map(Response::isSuccessful);
    }

}
