package com.epam.final_task.service.impl;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.impl.PlaylistTrackDao;
import com.epam.final_task.model.entity.PlaylistTrack;
import com.epam.final_task.service.PlaylistTrackService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Optional;

public class PlaylistTrackServiceImpl implements PlaylistTrackService {
    private static final Logger LOGGER = Logger.getLogger(PlaylistTrackServiceImpl.class);


    public void save(int playlistId, int cartTrackId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistTrackDao dao = factory.getPlaylistTrackDao();
            dao.save(new PlaylistTrack(playlistId, cartTrackId));
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to save relation", e);
        }
    }

    public Optional<PlaylistTrack> findByPlaylistIdAndTrackId(int playlistId, int trackId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistTrackDao dao = factory.getPlaylistTrackDao();
            return dao.findRelationByPlaylistIdAndTrackId(playlistId, trackId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to find relation", e);
        }
    }

    public void removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistTrackDao dao = factory.getPlaylistTrackDao();
            dao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to remove relation", e);
        }
    }
}
