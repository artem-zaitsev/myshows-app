package com.nikart.interactor.loaders;

import android.content.Context;

import com.nikart.interactor.loaders.base.BaseLoader;
import com.nikart.database.HelperFactory;
import com.nikart.model.dto.Episode;
import com.nikart.interactor.Answer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
