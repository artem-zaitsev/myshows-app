package com.nikart.interactor.loaders;

import android.content.Context;
import android.os.Bundle;

import com.nikart.base.BaseLoader;
import com.nikart.data.HelperFactory;
import com.nikart.interactor.Answer;

import java.sql.SQLException;

/**
 * Created by Artem on 14.03.2017.
 * Лоадер, загружающий информацию о сериале из базы
 */

public class ShowByIdFromDataBaseLoader extends BaseLoader<Answer> {

    public static final String ARGS_ID = "ID";

    private int id;

    public ShowByIdFromDataBaseLoader(Context context, int id) {
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
        try {
            data.setAnswer(HelperFactory.getHelper().getShowDAO().queryForId(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
