package com.nikart.data;

import android.content.Context;

import com.nikart.base.Response;
import com.nikart.base.BaseLoader;
import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 08.03.2017.
 */

public class ShowFromDataBaseLoader extends BaseLoader<Response>{


    public ShowFromDataBaseLoader(Context context) {
        super(context);
    }

    @Override
    public Response loadInBackground() {

        Response data = new Response();
        List<Show> generatedShows = new ArrayList<>(30);
        List<Episode> generatedEps = new ArrayList<>(30);

        for (int i = 0; i < 30; i++) {
            generatedShows.add(i, new Show());
            generatedEps.add(i, new Episode());
            generatedEps.get(i).setShow(generatedShows.get(i));
            try {
                HelperFactory.getHelper().getShowDAO().createOrUpdate(generatedShows.get(i));
                HelperFactory.getHelper().getEpisodeDAO().createOrUpdate(generatedEps.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            data.setAnswer(HelperFactory.getHelper().getShowDAO().getAllShows());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}
