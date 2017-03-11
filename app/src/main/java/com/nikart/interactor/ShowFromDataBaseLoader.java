package com.nikart.interactor;

import android.content.Context;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.interactor.Answer;
import com.nikart.base.BaseLoader;
import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 08.03.2017.
 */

public class ShowFromDataBaseLoader extends BaseLoader<Answer>{


    public ShowFromDataBaseLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {

        Answer data = new Answer();
        List<Show> generatedShows = new ArrayList<>(30);
        List<Episode> generatedEps = new ArrayList<>(30);


        for (int i = 0; i < 30; i++) {
            generatedShows.add(i, new Show());
            generatedEps.add(i, new Episode());
            generatedEps.get(i).setShow(generatedShows.get(i));
            try {
                HelperFactory.getHelper().getShowDAO().createOrUpdate(generatedShows.get(i));
                HelperFactory.getHelper().getEpisodeDAO().createOrUpdate(generatedEps.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            data.setAnswer(HelperFactory.getHelper().getShowDAO().getAllShows());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Тест
        try {
            retrofit2.Response<UserProfile> response = App.getApi().getUserProfile("RetAm").execute();
            UserProfile user = response.body();
            Log.d("FROM_API", (String) user.getLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}