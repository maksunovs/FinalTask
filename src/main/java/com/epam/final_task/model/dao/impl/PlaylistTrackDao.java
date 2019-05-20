package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.PlaylistTrack;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class PlaylistTrackDao extends AbstractDao<PlaylistTrack> {
    private static final String TABLE_NAME = "playlists_tracks";
    private static final String FIND_BY_PLAYLIST_ID_AND_TRACK_ID = "SELECT *FROM playlists_tracks WHERE playlist_id=? AND track_id=?;";

    public PlaylistTrackDao(Connection connection, Builder<PlaylistTrack> builder) {
        super(connection, builder);
    }

    public Optional<PlaylistTrack> findRelationByPlaylistIdAndTrackId(int playlistId, int trackId) throws DaoException {
        return executeQueryAsSingleResult(FIND_BY_PLAYLIST_ID_AND_TRACK_ID,playlistId,trackId);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }


    @Override
    protected Map<String, Object> getParameters(PlaylistTrack playlistTrack) {
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("playlist_id", playlistTrack.getPlaylistId());
        parameters.put("track_id", playlistTrack.getTrackId());
        return parameters;
    }
}
