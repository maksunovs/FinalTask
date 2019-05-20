package com.epam.final_task.service.impl;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.impl.ArtistDao;
import com.epam.final_task.model.entity.Artist;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ArtistServiceImpl implements ArtistService {

    private static final Logger LOGGER = Logger.getLogger(ArtistServiceImpl.class);


    public void save(Artist artist) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            ArtistDao artistDao = factory.getArtistDao();
            artistDao.save(artist);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to save artist", e);
        }
    }

    public Optional<Artist> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            ArtistDao artistDao = factory.getArtistDao();
            return artistDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload artist", e);
        }
    }


    public List<Artist> findAll() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            ArtistDao artistDao = factory.getArtistDao();
            return artistDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload artist", e);
        }
    }

    public void removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            ArtistDao artistDao = factory.getArtistDao();
            artistDao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to delete artist", e);
        }
    }
}
