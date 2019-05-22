package com.epam.final_task.service.impl;

import com.epam.final_task.model.dao.impl.*;
import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
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
            throw new ServiceException("Failed to remove tracks", e);
        }
    }

    public void addTrackToAlbum(int trackId, int albumId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            TrackDao trackDao = factory.getTrackDao();
            trackDao.addTrackToAlbum(trackId, albumId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to add track to album", e);
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
            throw new ServiceException("Failed to upload track", e);
        }
    }

    public void buyTrack(User user, Track track) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            try {
                TrackDao trackDao = factory.getTrackDao();
                List<Track> purchasedTracks = trackDao.findPurchasedTracks(user.getId());
                OrderDao orderDao = factory.getOrderDao();
                Optional<Order> order = orderDao.findByUserId(user.getId());
                List<Track> orderedTracks = new ArrayList<>();
                if (order.isPresent()) {
                    orderedTracks = trackDao.findOrderedTracks(order.get().getId());
                }
                BigDecimal trackPrice = track.getPrice();
                UserDao userDao = factory.getUserDAO();
                BigDecimal clientCash = userDao.findCash(user.getId());
                if (purchasedTracks.contains(track) || trackPrice.compareTo(clientCash) > 0) {
                    return;
                }

                factory.startTransaction();
                if (orderedTracks.contains(track)) {
                    OrderTrackDao orderTrackDao = factory.getOrderTrackDao();
                    Optional<OrderTrack> orderTrack = orderTrackDao.findByOrderIdAndTrackId(order.get().getId(), track.getId());
                    if (orderTrack.isPresent()) {
                        orderTrackDao.removeById(orderTrack.get().getId());
                    }
                }
                UserTrackDao userTrackDao = factory.getUserTrackDao();
                userTrackDao.save(new UserTrack(user.getId(), track.getId()));
                BigDecimal newCash = clientCash.add(trackPrice.negate());
                userDao.updateClientCash(newCash, user.getId());
                ((Client) user).setCash(newCash);
                factory.finishTransaction();

            } catch (DaoException e) {
                try {
                    factory.rollback();
                } catch (DaoException err) {
                    LOGGER.error(err.getMessage());
                    throw new ServiceException("Failed to buy track", e);
                }
                LOGGER.error(e.getMessage());
                throw new ServiceException("Failed to buy track", e);
            }
        }
    }
}
