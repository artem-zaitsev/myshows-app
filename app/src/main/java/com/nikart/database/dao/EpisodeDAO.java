package com.nikart.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.nikart.database.HelperFactory;
import com.nikart.model.dto.Episode;
import com.nikart.model.dto.Show;

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
                for (Episode e: episodes) {
                    Show s = HelperFactory.getHelper().getShowDAO().queryForId(e.getShowId());
                    e.setShow(s);
                }
                this.createOrUpdate(ep);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*delete all episodes*/
    public void deleteAll() throws SQLException {
            delete(getAllEpisodes());

    }
}
