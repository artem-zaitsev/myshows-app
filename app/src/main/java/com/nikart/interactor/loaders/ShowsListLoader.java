package com.nikart.interactor.loaders;

import android.content.Context;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
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
 * Created by Nikita on 17.03.2017.
 */

public class ShowsListLoader extends BaseLoader<Answer> {

    public ShowsListLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {
        List<Show> shows = null;
        data = new Answer();

        Response<ResponseBody> response = null;
        try {
            response = App.getInstance().getApi().getShows().execute();
            Log.d("LOADERS", this.toString()
                    + "ResponseBody: " + response.body() + " Message: " + response.message());

            JsonParser<Show> parser = new JsonParser<>(response);
            shows = parser.getParsedList(Show.class);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

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
