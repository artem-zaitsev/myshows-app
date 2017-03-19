package com.nikart.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.nikart.data.dto.Episode;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 05.03.2017.
 */

public class EpisodeDAO extends BaseDaoImpl<Episode, Integer> {

    public EpisodeDAO(ConnectionSource connectionSource,
                      Class<Episode> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Episode> getAllEpisodes() throws SQLException {
        return this.queryForAll();
    }

    public void createInDataBase(List<Episode> episodes) {
        for (Episode ep : episodes) {
            try {
                this.createOrUpdate(ep);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
