package com.nikart.interactor.loaders;

import android.content.Context;

import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.model.dto.Show;
import com.nikart.interactor.Answer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 17.03.2017.
 */

@Deprecated
public class ShowsListLoader extends BaseLoader<Answer> {

    public ShowsListLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {
        List<Show> shows = null;
        data = new Answer();

        /*Response<ResponseBody> response = null;
        try {
            response = App.getInstance().getApi().getShows().execute();
            Log.d("LOADERS", this.toString()
                    + "ResponseBody: " + response.body() + " Message: " + response.message());

            JsonParser<Show> parser = new JsonParser<>(response.body());
            shows = parser.getParsedList(Show.class);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }*/

        if (shows != null) {
            createShowsInDb(shows);
        } else {
            shows = getShowsFromDb();
        }
        data.setAnswer(shows);
        return data;
    }

    private void createShowsInDb(List<Show> shows) {
        try {
            HelperFactory.getHelper().getShowDAO().createInDataBase(shows);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Show> getShowsFromDb() {
        List<Show> shows = new ArrayList<>();
        try {
            shows = HelperFactory.getHelper().getShowDAO().getAllShows();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shows;
    }
}
