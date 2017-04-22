package com.nikart.interactor.loaders;

import android.content.Context;
import android.util.Log;

import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.model.dto.Show;
import com.nikart.interactor.Answer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 08.03.2017.
 * Without internet connection
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

    private List<Show> getFromDataBase() throws SQLException {
        return HelperFactory.getHelper().getShowDAO().getAllShows();
    }
}
