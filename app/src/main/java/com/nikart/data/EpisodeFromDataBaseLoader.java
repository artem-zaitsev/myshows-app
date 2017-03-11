package com.nikart.data;

import android.content.Context;

import com.nikart.base.BaseAnswer;
import com.nikart.base.BaseLoader;

import java.sql.SQLException;

/**
 * Created by Artem on 09.03.2017.
 */

public class EpisodeFromDataBaseLoader extends BaseLoader<BaseAnswer> {

    public EpisodeFromDataBaseLoader(Context context) {
        super(context);
    }


    @Override
    public BaseAnswer loadInBackground() {
        BaseAnswer data = new BaseAnswer();
        try {
            data.setAnswer(HelperFactory.getHelper().getEpisodeDAO().getAllEpisodes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}
