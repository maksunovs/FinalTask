package com.epam.final_task.model.dao;

import com.epam.final_task.builder.*;
import com.epam.final_task.model.dao.connection.ConnectionPool;
import com.epam.final_task.model.dao.exception.ConnectionException;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.impl.*;
import com.epam.final_task.util.Hasher;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

    private Connection connection;

    public DaoFactory() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public PlaylistTrackDao getPlaylistTrackDao() {
        return new PlaylistTrackDao(connection, new PlaylistTrackBuilder());
    }

    public UserTrackDao getUserTrackDao() {
        return new UserTrackDao(connection, new UserTrackBuilder());
    }

    public CartDao getCartDao() {
        return new CartDao(connection, new CartBuilder());
    }

    public ArtistDao getArtistDao() {
        return new ArtistDao(connection, new ArtistBuilder());
    }

    public UserDao getUserDAO() {
        return new UserDao(connection, new UserBuilder(), new Hasher());
    }

    public TrackDao getTrackDao() {
        return new TrackDao(connection, new TrackBuilder());
    }

    public AlbumDao getAlbumDao() {
        return new AlbumDao(connection, new AlbumBuilder());
    }

    public PlaylistDao getPlaylistDao() {
        return new PlaylistDao(connection, new PlaylistBuilder());
    }

    public CartTrackDao getCartTrackDao() {
        return new CartTrackDao(connection, new CartTrackBuilder());
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException("Failed to start transaction", e);
        }
    }

    public void finishTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException("Failed to finish transaction", e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException("Failed to rollback transaction", e);
        }
    }

    @Override
    public void close() throws ConnectionException {
        ConnectionPool.getInstance().returnConnection(connection);
    }
}
