package com.nikart.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.nikart.dto.Episode;

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
}
