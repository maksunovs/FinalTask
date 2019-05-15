package com.epam.final_task.model.dao.implementation;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.entity.Playlist;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlaylistDao extends AbstractDao<Playlist> {

    private static final String TABLE_NAME = "playlists";
    private static final String FIND_ALL = "SELECT *FROM playlists";
    private static final String FIND_BY_ID = "SELECT *FROM playlists WHERE id=?;";

    public PlaylistDao(Connection connection, Builder<Playlist> builder) {
        super(connection, builder);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(Playlist playlist) {
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("id",playlist.getId());
        parameters.put("title",playlist.getTitle());
        return parameters;
    }

}
