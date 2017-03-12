package com.nikart.interactor;

import android.content.Context;

import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 12.03.2017.
 */

public class ShowFromWebLoader extends BaseLoader<Answer> {

    public ShowFromWebLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {
        //Проверка
        data = new Answer();
        List<Show> generatedShows = new ArrayList<>(30);
        List<Episode> generatedEps = new ArrayList<>(30);

        for (int i = 0; i < 30; i++) {
            generatedShows.add(i, new Show());
            generatedEps.add(i, new Episode());
            generatedShows.get(i).setId(i);
            generatedEps.get(i).setShow(generatedShows.get(i));
            try {
                HelperFactory.getHelper().getShowDAO().createIfNotExists(generatedShows.get(i));
                HelperFactory.getHelper().getEpisodeDAO().createIfNotExists(generatedEps.get(i));
                data.setAnswer(true);
            } catch (SQLException e) {
                e.printStackTrace();
                data.setAnswer(false);
            }
        }
        return data;
}
}
