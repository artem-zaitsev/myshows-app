package com.nikart.interactor;

import android.content.Context;

import com.nikart.data.HelperFactory;
import com.nikart.interactor.Answer;
import com.nikart.base.BaseLoader;

import java.sql.SQLException;

/**
 * Created by Artem on 09.03.2017.
 */

public class EpisodeFromDataBaseLoader extends BaseLoader<Answer> {

    public EpisodeFromDataBaseLoader(Context context) {
        super(context);
    }


    @Override
    public Answer loadInBackground() {
        Answer data = new Answer();
        try {
            data.setAnswer(HelperFactory.getHelper().getEpisodeDAO().getAllEpisodes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}
