package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.Track;

import java.sql.Connection;
import java.util.*;

public class TrackDao extends AbstractDao<Track> {
    private static final String TABLE_NAME = "tracks";
    private static final String FIND_ALL = "SELECT tracks.id,tracks.artist_id, tracks.title,tracks.genre,tracks.price, tracks.album_id, artists.name" +
            " FROM tracks JOIN artists ON tracks.artist_id=artists.id;";
    private static final String FIND_BY_ID = "SELECT tracks.id,tracks.artist_id,tracks.title,tracks.genre,tracks.price, tracks.album_id, artists.name " +
            "FROM  tracks JOIN artists ON tracks.artist_id=artists.id and tracks.id=?;";
    private static final String FIND_BY_GENRE =
            "SELECT tracks.id,tracks.artist_id,tracks.title,tracks.genre,tracks.price, tracks.album_id, artists.name " +
                    "FROM tracks " +
                    "JOIN artists ON tracks.artist_id=artists.id and tracks.genre=?;";
    private static final String FIND_BY_PLAYLIST_ID = "SELECT tracks.id,tracks.artist_id,tracks.title,tracks.genre,tracks.price, tracks.album_id, artists.name " +
            "FROM  tracks JOIN artists ON tracks.artist_id=artists.id and tracks.id in" +
            "(select track_id from playlists_tracks where playlist_id=?);";
    private static final String FIND_NOT_IN_PLAYLIST = "SELECT tracks.id,tracks.artist_id,tracks.title,tracks.genre,tracks.price, tracks.album_id, artists.name " +
            "FROM  tracks JOIN artists ON tracks.artist_id=artists.id and tracks.id not in" +
            "(select track_id from playlists_tracks where playlist_id=?);";
    private static final String FIND_BY_ARTIST_ID = "SELECT tracks.id,tracks.artist_id,tracks.title,tracks.genre,tracks.price, tracks.album_id, artists.name " +
            "FROM  tracks JOIN artists ON tracks.artist_id=artists.id and tracks.artist_id=?;";
    private static final String FIND_BY_ALBUM_ID = "SELECT tracks.id,tracks.artist_id,tracks.title,tracks.genre,tracks.price, tracks.album_id, artists.name " +
            "FROM  tracks JOIN artists ON tracks.artist_id=artists.id and tracks.album_id=?;";
    private static final String FIND_SINGLES_BY_ARTIST_ID = "SELECT tracks.id,tracks.artist_id,tracks.title,tracks.genre,tracks.price, tracks.album_id, artists.name " +
            "FROM  tracks JOIN artists ON tracks.artist_id=artists.id and tracks.artist_id=? AND tracks.album_id is null;";
    private static final String REMOVE_TRACK_FROM_ALBUM = "UPDATE tracks SET album_id=null WHERE id=?;";
    private static final String ADD_TRACK_TO_ALBUM = "UPDATE tracks SET album_id=? WHERE id=?;";
    private static final String FIND_PURCHASED_TRACKS = "SELECT * FROM tracks JOIN artists ON tracks.artist_id=artists.id" +
            " WHERE tracks.id IN " +
            "(SELECT  track_id FROM users_tracks WHERE user_id=?);";
    private static final String FIND_IN_CART = "SELECT * FROM tracks JOIN artists ON tracks.artist_id=artists.id " +
            "WHERE tracks.id IN " +
            "(SELECT  track_id FROM orders_tracks WHERE order_id=?);";
    public TrackDao(Connection connection, Builder<Track> builder) {
        super(connection, builder);
    }

    public List<Track> findByAlbumId(int id) throws DaoException {
        return executeQuery(FIND_BY_ALBUM_ID, id);
    }

    public List<Track> findSinglesByArtistId(int id) throws DaoException {
        return executeQuery(FIND_SINGLES_BY_ARTIST_ID, id);
    }

    public List<Track> findByPlaylistId(int id) throws DaoException {
        return executeQuery(FIND_BY_PLAYLIST_ID, id);
    }
    public List<Track> findPurchasedTracks(int userId) throws DaoException {
        return executeQuery(FIND_PURCHASED_TRACKS,userId);
    }
    public List<Track> findNotInPlaylist(int playlist_id) throws DaoException {
        return executeQuery(FIND_NOT_IN_PLAYLIST, playlist_id);
    }

    public List<Track> findByArtistId(int id) throws DaoException {
        return executeQuery(FIND_BY_ARTIST_ID, id);
    }
    public List<Track> findTracksInCart(int cartId) throws DaoException {
        return executeQuery(FIND_IN_CART,cartId);
    }
    @Override
    public List<Track> findAll() throws DaoException {
        return executeQuery(FIND_ALL);
    }

    @Override
    public Optional<Track> findById(int id) throws DaoException {
        return executeQueryAsSingleResult(FIND_BY_ID, id);
    }

    public List<Track> findByGenre(String genre) throws DaoException {
        return executeQuery(FIND_BY_GENRE, genre);
    }

    public void removeTrackFromAlbum(int id) throws DaoException {
        executeUpdate(REMOVE_TRACK_FROM_ALBUM, id);
    }

    public void addTrackToAlbum(int trackId, int albumId) throws DaoException {
        executeUpdate(ADD_TRACK_TO_ALBUM, albumId, trackId);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(Track track) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("artist_id", track.getArtist());
        parameters.put("title", track.getTitle());
        parameters.put("genre", track.getGenre());
        parameters.put("price", track.getPrice());
        return parameters;
    }
}
