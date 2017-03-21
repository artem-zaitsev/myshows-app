package com.nikart.interactor.loaders;

import android.content.Context;
import android.util.Log;

import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;
import com.nikart.interactor.Answer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 08.03.2017.
 */

public class ShowsListFromDataBaseLoader extends BaseLoader<Answer> {

    public ShowsListFromDataBaseLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {

        data = new Answer();
        try {
            data.setAnswer(getFromDataBase());
            Log.d("LOADERS", String.valueOf(getFromDataBase().size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    private void createInDataBase(List<Show> generatedShows,
                                  List<Episode> generatedEps) throws IOException {
        //Запрос работает, но крэш при парсинге.
        //retrofit2.Response response = App.getInstance().getApi().getShows().execute();
        for (int i = 0; i < 30; i++) {
            try {
                HelperFactory.getHelper().getShowDAO().createOrUpdate(generatedShows.get(i));
                HelperFactory.getHelper().getEpisodeDAO().createOrUpdate(generatedEps.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Show> getFromDataBase() throws SQLException {
        return HelperFactory.getHelper().getShowDAO().getAllShows();
    }
}
