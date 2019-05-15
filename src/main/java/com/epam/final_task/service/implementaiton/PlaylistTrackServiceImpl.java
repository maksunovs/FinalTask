package com.epam.final_task.service.implementaiton;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.implementation.PlaylistTrackDao;
import com.epam.final_task.model.entity.PlaylistTrack;
import com.epam.final_task.service.PlaylistTrackService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Optional;

public class PlaylistTrackServiceImpl implements PlaylistTrackService {
    private static final Logger LOGGER = Logger.getLogger(PlaylistTrackServiceImpl.class);


    public void save(int playlistId, int audioTrackId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistTrackDao dao = factory.getPlaylistTrackDao();
            dao.save(new PlaylistTrack(playlistId, audioTrackId));
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to delete playlist", e);
        }
    }

    public Optional<PlaylistTrack> findByPlaylistIdAndAudioTrackId(int playlistId, int audiotrackId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistTrackDao dao = factory.getPlaylistTrackDao();
            return dao.findRelationByPlaylistIdAndTrackId(playlistId,audiotrackId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to delete playlist", e);
        }
    }

    public void removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistTrackDao dao = factory.getPlaylistTrackDao();
            dao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to delete playlist", e);
        }
    }
}
