package com.nikart.interactor.loaders;

import android.content.Context;

import com.nikart.interactor.loaders.base.BaseLoader;
import com.nikart.database.HelperFactory;
import com.nikart.interactor.Answer;

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
