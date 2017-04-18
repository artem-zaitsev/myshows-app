package com.nikart.interactor;

import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.interactor.retrofit.MyShowsApi;
import com.nikart.util.JsonParser;
import com.nikart.util.PreferencesWorker;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public Observable<Boolean> signIn(String login, String password) {
        return api.signIn(login, password)
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

    public Observable<UserProfile> getUserProfile() {
        return App.getInstance().getApi().getUserProfile();
    }

    public Observable<Boolean> updateRateShow(int showId, int rate) {
        return App.getInstance().getApi().updateShowRate(showId, rate)
                .map(Response::isSuccessful);
    }

}
