package com.nikart.interactor;

import android.content.Context;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.util.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Nikita on 17.03.2017.
 */

public class ShowsListLoader extends BaseLoader<Answer> {

    public ShowsListLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {
        List<Show> shows = new ArrayList<>();

        data = new Answer();

        JSONObject result = null;
//        Call<ResponseBody> responseBodyCall = App.getInstance().getApi().getShows();

        OkHttpClient client = App.getInstance().getClient();
        /**/

        Request request = new Request.Builder()
                .url("https://api.myshows.me/profile/shows/")
                .build();

        //Response<ResponseBody> response = null;
        okhttp3.Response response = null;

        ResponseBody responseBody =null;
        try {
            //response = App.getInstance().getApi().getShows().execute();
            response = client.newCall(request).execute();
            responseBody = response.body();
            Log.d("JSON","ResponseBody: " + response.body() + " Message: " + response.message() );
            result = response.body()!=null ? new JSONObject(response.body().string()) : null;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        JsonParser<Show> parser = new JsonParser<>();
        if (result != null) {
            shows = parser.getParsedList(result, Show.class);
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
