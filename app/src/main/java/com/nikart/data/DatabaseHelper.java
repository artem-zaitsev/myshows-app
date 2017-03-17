package com.nikart.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nikart.data.dao.EpisodeDAO;
import com.nikart.data.dao.ShowDAO;
import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 05.03.2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getName();

    private static final String DATABASE_NAME = "myshows.db";

    private static final int DATABASE_VERSION = 1;

    private ShowDAO showDAO = null;
    private EpisodeDAO episodeDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Show.class);
            TableUtils.createTable(connectionSource, Episode.class);
        } catch (SQLException e) {
            Log.d("DATABASE_LOG", "error creating db " + DATABASE_NAME);
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Show.class, true);
            TableUtils.dropTable(connectionSource, Episode.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.d("DATABASE_LOG", "error upgrading db "
                    + DATABASE_NAME + " ver. " + DATABASE_VERSION);
            e.printStackTrace();
        }
    }

    /*Create a notes in base*/
    public void createInDataBase(List<Show> shows) throws IOException {
        for (Show s : shows) {
            try {
                getShowDAO().createOrUpdate(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*Show*/
    public ShowDAO getShowDAO() throws SQLException {
        if (showDAO == null) {
            showDAO = new ShowDAO(getConnectionSource(), Show.class);
        }
        return showDAO;
    }

    /*Episode*/
    public EpisodeDAO getEpisodeDAO() throws SQLException {
        if (episodeDAO == null) {
            episodeDAO = new EpisodeDAO(getConnectionSource(), Episode.class);
        }
        return episodeDAO;
    }

    @Override
    public void close() {
        showDAO = null;
        episodeDAO = null;
        super.close();
    }
}
