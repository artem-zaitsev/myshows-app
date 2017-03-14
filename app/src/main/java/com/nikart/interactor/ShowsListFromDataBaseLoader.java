package com.nikart.interactor;

import android.content.Context;
import android.util.Log;

import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;

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
        List<Show> generatedShows = new ArrayList<>(30);
        List<Episode> generatedEps = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            generatedShows.add(i, new Show());
            generatedShows.get(i).setId(i);
            generatedEps.add(i, new Episode());
            generatedEps.get(i).setShow(generatedShows.get(i));
        }

        data = new Answer();
        try {
            if (getFromDataBase().size() < generatedShows.size()) {
                createInDataBase(generatedShows, generatedEps);
            }

            data.setAnswer(getFromDataBase());

            Log.d("LOADERS", String.valueOf(getFromDataBase().size()));
        } catch (SQLException | IOException e) {
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
