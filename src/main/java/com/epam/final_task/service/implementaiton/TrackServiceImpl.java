package com.epam.final_task.service.implementaiton;

import com.epam.final_task.model.dao.implementation.TrackDao;
import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.implementation.UserDao;
import com.epam.final_task.model.dao.implementation.UserTrackDao;
import com.epam.final_task.model.entity.Client;
import com.epam.final_task.model.entity.Track;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.model.entity.UserTrack;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TrackServiceImpl implements TrackService {
    private static final Logger LOGGER = Logger.getLogger(TrackServiceImpl.class);

    public List<Track> findAll() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public void save(Track track) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            trackDao.save(track);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to save track", e);
        }
    }

    public List<Track> findByGenre(String genre) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findByGenre(genre);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public void deleteById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            trackDao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to delete track", e);
        }
    }

    public List<Track> findByPlaylistId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findByPlaylistId(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public List<Track> findNotInPlaylist(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findNotInPlaylist(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public List<Track> findByArtistId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findByArtistId(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public List<Track> findByAlbumId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findByAlbumId(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public List<Track> findSinglesByArtistId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findSinglesByArtistId(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public void removeTrackFromAlbum(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            trackDao.removeTrackFromAlbum(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public void addTrackToAlbum(int trackId, int albumId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            trackDao.addTrackToAlbum(trackId, albumId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public List<Track> findPurchasedTracks(int userId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findPurchasedTracks(userId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public List<Track> findOrderedTracks(int orderId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findOrderedTracks(orderId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }

    public Optional<Track> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            return trackDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to upload tracks", e);
        }
    }
    public void buyTrack(User user, Track track) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            try {
                TrackDao trackDao = factory.getTrackDao();
                List<Track> purchasedTracks = trackDao.findPurchasedTracks(user.getId());
                if (purchasedTracks.contains(track)||track.getPrice().compareTo(((Client)user).getCash())>0) {
                    return;
                }
                factory.startTransaction();
                UserDao userDao = factory.getUserDAO();
                BigDecimal cash = ((Client) user).getCash();
                UserTrackDao userTrackDao = factory.getUserTrackDao();
                userTrackDao.save(new UserTrack(user.getId(), track.getId()));
                BigDecimal newCash = cash.add(track.getPrice().negate());
                userDao.updateClientCash(newCash, user.getId());
                ((Client) user).setCash(newCash);
                factory.finishTransaction();

            } catch (DaoException e) {
                try {
                    factory.rollback();
                } catch (DaoException err) {
                    throw new ServiceException("Failed to buy track", e);
                }
                throw new ServiceException("Failed to buy track", e);
            }
        }
    }
}
