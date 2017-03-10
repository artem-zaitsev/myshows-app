package com.nikart.data;

import android.content.Context;

import com.nikart.base.Response;
import com.nikart.base.BaseLoader;

import java.sql.SQLException;

/**
 * Created by Artem on 09.03.2017.
 */

public class EpisodeFromDataBaseLoader extends BaseLoader<Response> {

    public EpisodeFromDataBaseLoader(Context context) {
        super(context);
    }


    @Override
    public Response loadInBackground() {
        Response data = new Response();
        try {
            data.setAnswer(HelperFactory.getHelper().getEpisodeDAO().getAllEpisodes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}
