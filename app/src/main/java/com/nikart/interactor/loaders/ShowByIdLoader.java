package com.nikart.interactor.loaders;

import android.content.Context;
import android.os.Bundle;

import com.nikart.base.BaseLoader;
import com.nikart.model.dto.Show;
import com.nikart.interactor.Answer;

/**
 * Created by Artem on 14.03.2017.
 * Лоадер, загружающий информацию о сериале из базы
 */

@Deprecated
public class ShowByIdLoader extends BaseLoader<Answer> {

    public static final String ARGS_ID = "ID";

    private int id;

    public ShowByIdLoader(Context context, int id) {
        super(context);
        this.id = id;
    }

    public static Bundle args(int id) {
        Bundle args = new Bundle();
        args.putInt(ARGS_ID, id);
        return args;
    }

    @Override
    public Answer loadInBackground() {
        data = new Answer();
        Show show = null;
       /* try {
            show = HelperFactory.getHelper().getShowDAO().queryForId(id);
            String watchStatus = show.getWatchStatus();

            Response<Show> response = App.getInstance().getApi().getShowById(id).execute();

            if (response.body() != null) {
                show = response.body();
                show.setId(id);
                show.setWatchStatus(watchStatus);
                HelperFactory.getHelper().getShowDAO().update(show);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }*/
        data.setAnswer(show);
        return data;
    }
}
