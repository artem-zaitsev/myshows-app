package com.nikart.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 09.03.2017.
 */

public class EpisodeFromDataBaseLoader extends AsyncTaskLoader<List<Episode>> {

    private List<Episode> data;

    public EpisodeFromDataBaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (data != null) {
            deliverResult(data);
        }
        forceLoad();
    }

    @Override
    public List<Episode> loadInBackground() {

        try {
            data = HelperFactory.getHelper().getEpisodeDAO().getAllEpisodes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void deliverResult(List<Episode> data) {
        this.data = data;
        super.deliverResult(data);
    }
}
