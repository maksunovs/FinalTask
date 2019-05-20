package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.Album;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AlbumDao extends AbstractDao<Album> {
    private static final String TABLE_NAME = "albums";
    private static final String FIND_BY_ARTIST_ID = "SELECT *FROM albums WHERE artist_id=?;";

    public AlbumDao(Connection connection, Builder<Album> builder) {
        super(connection, builder);
    }

    public List<Album> findByArtistId(int id) throws DaoException {
        return executeQuery(FIND_BY_ARTIST_ID,id);
    }
    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(Album album) {
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("id",album.getId());
        parameters.put("title",album.getTitle());
        parameters.put("artist_id",album.getArtistId());
        return parameters;
    }
}
