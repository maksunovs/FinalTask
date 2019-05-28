package com.epam.final_task.service.impl;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.impl.*;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.CartService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class CartServiceImpl implements CartService {
    private static final Logger LOGGER = Logger.getLogger(CartServiceImpl.class);

    public Optional<Cart> findByUserId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            CartDao dao = factory.getCartDao();
            return dao.findByUserId(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to find cart", e);
        }
    }

    public void removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            CartDao dao = factory.getCartDao();
            dao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to remove cart", e);
        }
    }

    public void payCart(User user, int cartId, BigDecimal value) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            try {
                UserDao userDao = factory.getUserDAO();
                BigDecimal clientCash = userDao.findCash(user.getId());
                if (clientCash.compareTo(value) < 0) {
                    return;
                }
                factory.startTransaction();
                TrackDao trackDao = factory.getTrackDao();
                List<Track> tracks = trackDao.findTracksInCart(cartId);
                CartTrackDao cartTrackDao = factory.getCartTrackDao();
                UserTrackDao userTrackDao = factory.getUserTrackDao();
                List<CartTrack> cartTrackList = cartTrackDao.findByCartId(cartId);
                for (CartTrack cartTrack : cartTrackList) {
                    cartTrackDao.removeById(cartTrack.getId());
                }
                for (Track track : tracks) {
                    userTrackDao.save(new UserTrack(user.getId(), track.getId()));
                }
                BigDecimal cash = ((Client) user).getCash();
                BigDecimal newCash = cash.add(value.negate());
                userDao.updateClientCash(newCash, user.getId());
                ((Client) user).setCash(newCash);
                factory.finishTransaction();
            } catch (DaoException e) {
                try {
                    factory.rollback();
                } catch (DaoException err) {
                    LOGGER.error(e.getMessage());
                    throw new ServiceException("Failed to pay cart", e);
                }
                LOGGER.error(e.getMessage());
                throw new ServiceException("Failed to pay cart", e);
            }
        }
    }
}
