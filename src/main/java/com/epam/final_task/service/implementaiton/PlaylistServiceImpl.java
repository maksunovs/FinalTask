package com.epam.final_task.service.implementaiton;

import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.implementation.PlaylistDao;
import com.epam.final_task.model.entity.Playlist;
import com.epam.final_task.service.PlaylistService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PlaylistServiceImpl implements PlaylistService {

    private static final Logger LOGGER = Logger.getLogger(PlaylistServiceImpl.class);


    public List<Playlist> findAll() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistDao playlistDao = factory.getPlaylistDao();
            return playlistDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload playlist", e);
        }
    }

    public void save(Playlist playlist) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
          PlaylistDao playlistDao = factory.getPlaylistDao();
            playlistDao.save(playlist);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to save playlist", e);
        }
    }

    public void deleteById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistDao playlistDao = factory.getPlaylistDao();
            playlistDao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to delete playlist", e);
        }
    }
    public Optional<Playlist> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PlaylistDao playlistDao = factory.getPlaylistDao();
            return playlistDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to delete playlist", e);
        }
    }
}
