package com.nikart.data.dao;

import android.util.Log;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.nikart.model.dto.Show;
import com.nikart.model.dto.ShowTmp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 02.05.2017.
 */

public class ShowTmpDAO  extends BaseDaoImpl<ShowTmp, Integer>{

    public ShowTmpDAO(ConnectionSource connectionSource, Class<ShowTmp> dataClass)
            throws SQLException {
        super(connectionSource, dataClass);
    }


    public List<ShowTmp> getAllShows() throws SQLException {
        return this.queryForAll();
    }

    /*Create a notes in base*/
    public <T extends ShowTmp> void createInDataBase(List<T> shows) {
        for (T s : shows) {
            try {
                this.createIfNotExists(s);
            } catch (SQLException e) {
                Log.d("DB", e.toString());
            }
        }
    }

    /*compare with ShowTable*//*
    public List<Show> checkWatchingShows() throws SQLException {
        List<Show> shows = new ArrayList<>();
        shows.addAll(this.queryBuilder()
                .where()
                .eq(ShowTmp.FIELD_NAME_WATCH_STATUS, "watching")
                .query());
        return shows;
    }*/

    /*delete all shows*/
    public void deleteAll() throws SQLException {
        this.delete(getAllShows());
    }
}
