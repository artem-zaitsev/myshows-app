package com.nikart.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.nikart.model.dto.Show;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 05.03.2017.
 */

public class ShowDAO extends BaseDaoImpl<Show, Integer> {

    public ShowDAO(ConnectionSource connectionSource,
                   Class<Show> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Show> getAllShows() throws SQLException {
        return this.queryForAll();
    }

    /*Create a notes in base*/
    public void createInDataBase(List<Show> shows) throws IOException {
        for (Show s : shows) {
            try {
                this.createIfNotExists(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*delete all shows*/
    public void deleteAll() throws SQLException {
            this.delete(getAllShows());
    }
}
