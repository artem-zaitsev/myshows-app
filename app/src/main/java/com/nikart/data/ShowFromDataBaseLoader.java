package com.nikart.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 08.03.2017.
 */

public class ShowFromDataBaseLoader extends AsyncTaskLoader<List<Show>> {

    private List<Show> shows;

    public ShowFromDataBaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (shows != null) {
            deliverResult(shows);
        } else {
            forceLoad();
        }

    }

    @Override
    public List<Show> loadInBackground() {
        try {
            shows = HelperFactory.getHelper().getShowDAO().getAllShows();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shows;
    }

    @Override
    public void deliverResult(List<Show> data) {
        this.shows = data;
        super.deliverResult(data);
    }
}
