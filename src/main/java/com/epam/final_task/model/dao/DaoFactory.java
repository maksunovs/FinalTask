package com.epam.final_task.model.dao;

import com.epam.final_task.builder.*;
import com.epam.final_task.model.dao.connection.ConnectionPool;
import com.epam.final_task.model.dao.exception.ConnectionException;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.impl.*;
import com.epam.final_task.util.Hasher;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory implements AutoCloseable {
    private Connection connection;

    public DaoFactory()  {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public PlaylistTrackDao getPlaylistTrackDao() {
        return new PlaylistTrackDao(connection, new PlaylistTrackBuilder());
    }

    public UserTrackDao getUserTrackDao() {
        return new UserTrackDao(connection, new UserTrackBuilder());
    }

    public OrderDao getOrderDao() {
        return new OrderDao(connection, new OrderBuilder());
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

    public OrderTrackDao getOrderTrackDao() {
        return new OrderTrackDao(connection, new OrderTrackBuilder());
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Failed to start transaction", e);
        }
    }

    public void finishTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException("Failed to start transaction", e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException("Failed to start transaction", e);
        }
    }

    @Override
    public void close() throws ConnectionException {
        ConnectionPool.getInstance().returnConnection(connection);
    }
}
