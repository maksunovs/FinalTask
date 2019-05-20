package com.epam.final_task.service.impl;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.impl.AlbumDao;
import com.epam.final_task.model.entity.Album;
import com.epam.final_task.service.AlbumService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AlbumServiceImpl implements AlbumService {
    private static final Logger LOGGER = Logger.getLogger(AlbumServiceImpl.class);


    public List<Album> findByArtistId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            AlbumDao dao = factory.getAlbumDao();
            return dao.findByArtistId(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload artist", e);
        }
    }

    public Optional<Album> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            AlbumDao dao = factory.getAlbumDao();
            return dao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload artist", e);
        }
    }

    public void save(Album album) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            AlbumDao dao = factory.getAlbumDao();
            dao.save(album);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to save album", e);
        }
    }

    public void removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            AlbumDao dao = factory.getAlbumDao();
            dao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to delete album", e);
        }
    }
}
