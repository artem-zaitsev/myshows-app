package com.nikart.interactor;

import android.content.Context;

import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;

import java.sql.SQLException;

/**
 * Created by Artem on 09.03.2017.
 */

public class EpisodesListFromDataBaseLoader extends BaseLoader<Answer> {

    public EpisodesListFromDataBaseLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {
        data = new Answer();
        try {
            data.setAnswer(HelperFactory.getHelper().getEpisodeDAO().getAllEpisodes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}
