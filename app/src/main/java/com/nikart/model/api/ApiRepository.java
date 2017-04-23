package com.nikart.model.api;

import com.nikart.data.HelperFactory;
import com.nikart.interactor.retrofit.DaggerNetworkComponent;
import com.nikart.interactor.retrofit.MyShowsApi;
import com.nikart.interactor.retrofit.NetworkHelper;
import com.nikart.model.Model;
import com.nikart.model.dto.Episode;
import com.nikart.model.dto.Show;
import com.nikart.model.dto.UserProfile;
import com.nikart.util.JsonParser;
import com.nikart.util.Md5Converter;
import com.nikart.util.PreferencesWorker;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Artem on 16.04.2017.
 * Manager for API api.myshows.me.
 */

public class ApiRepository implements Model {

    private static ApiRepository apiRepository;

    @Inject
    public NetworkHelper helper;
    @Inject
    public MyShowsApi api;

    public ApiRepository() {
        DaggerNetworkComponent.create()
                .inject(this);
    }

    public static ApiRepository getInstance() {
        if (apiRepository == null) apiRepository = new ApiRepository();
        return apiRepository;
    }

    public Observable<Boolean> signIn(String login, String password) {
        Map<String, String> query = new HashMap<>();
        query.put("login", login);
        query.put("password", Md5Converter.Md5Hash(password));
        return api.signIn(query)
                .map(response -> {
                    if (response.isSuccessful()) {
                        PreferencesWorker.getInstance().saveLogin(login);
                        PreferencesWorker.getInstance().savePassword(password);
                    }
                    return response.isSuccessful();
                });
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

    public Observable<List<List<Episode>>> getNextEpisodes() {
        return api.getNextEpisodes()
                .map(
                        body -> {
                            JsonParser<Episode> parser = new JsonParser<>(body);
                            List<Episode> episodes = parser.getParsedList(Episode.class);
                            HelperFactory.getHelper().getEpisodeDAO().createInDataBase(episodes);
                            return episodes;
                        }
                )
                .map(episodes -> {
                    List<List<Episode>> groups = new ArrayList<>();
                    Date today = new Date();
                    Date maximumDate = new Date(121212);

                    //TODO: придумать алгоритм, как поэффективней разбивать по месяцам
                    for (Episode ep : episodes) {
                        maximumDate = (today.compareTo(ep.getAirDate()) < 0)
                                ? ep.getAirDate()
                                : today;
                    }

                    Calendar maxCalendar = Calendar.getInstance();
                    Calendar airCalendar = Calendar.getInstance();
                    maxCalendar.setTime(maximumDate);
                    for (int i = Calendar.getInstance().get(Calendar.MONTH);
                         i <= maxCalendar.get(Calendar.MONTH); i++) {
                        List<Episode> tmp = new ArrayList<>();
                        for (Episode ep : episodes) {
                            airCalendar.setTime(ep.getAirDate());
                            if (today.compareTo(ep.getAirDate()) <= 0 &&
                                    airCalendar.get(Calendar.MONTH) == i) {
                                tmp.add(ep);
                            }
                        }
                        if (tmp.isEmpty()) continue;
                        groups.add(tmp);
                    }
                    return groups;
                });
    }

    public Observable<UserProfile> getUserInfo() {
        return api.getUserProfile();
    }

    public Observable<Boolean> updateRateShow(int showId, int rate) {
        return api.updateShowRate(showId, rate)
                .map(Response::isSuccessful);
    }

    public Observable<Show> getShowById(int id) {
        return api.getShowById(id)
                .map(sh -> {
                    Show tmpShow = null;
                    tmpShow = HelperFactory.getHelper().getShowDAO().queryForId(id);
                    String watchStatus = tmpShow.getWatchStatus();
                    sh.setId(id);
                    sh.setWatchStatus(watchStatus);
                    HelperFactory.getHelper().getShowDAO().update(sh);
                    return sh;
                });
    }
}
