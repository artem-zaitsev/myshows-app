package com.nikart.interactor.loaders;

import android.content.Context;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Episode;
import com.nikart.interactor.Answer;
import com.nikart.util.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Artem on 19.03.2017.
 */

@Deprecated
public class NextEpisodesListLoader extends BaseLoader<Answer> {

    public NextEpisodesListLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {
        data = new Answer();

        List<Episode> episodes = null;
       /* try {
            Response<ResponseBody> response = App.getInstance().getApi().getNextEpisodes().execute();
            Log.d("LOADERS", this.toString() +
                    "ResponseBody: " + response.body() + " Message: " + response.message());
            JsonParser<Episode> parser = new JsonParser<>(response.body());
            episodes = parser.getParsedList(Episode.class);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }*/

        if (episodes != null) {
            createEpisodesInDb(episodes);
        } else {
            episodes = getEpisodesFromDb();
        }
        data.setAnswer(episodes);
        return data;
    }

    private List<Episode> getEpisodesFromDb() {
        List<Episode> eps = new ArrayList<>();
        try {
            eps = HelperFactory.getHelper().getEpisodeDAO().getAllEpisodes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eps;
    }

    private void createEpisodesInDb(List<Episode> episodes) {
        try {

            HelperFactory.getHelper().getEpisodeDAO().createInDataBase(episodes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
