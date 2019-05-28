package com.epam.final_task.service.impl;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.impl.CartTrackDao;
import com.epam.final_task.model.entity.CartTrack;
import com.epam.final_task.service.CartTrackService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Optional;

public class CartTrackServiceImpl implements CartTrackService {


    private static final Logger LOGGER = Logger.getLogger(CartTrackServiceImpl.class);

    public void save(CartTrack cartTrack) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            CartTrackDao cartTrackDao = factory.getCartTrackDao();
            cartTrackDao.save(cartTrack);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to add to cart", e);
        }

    }

    public Optional<CartTrack> findByCartIdAndTrackId(int cartId, int trackId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            CartTrackDao cartTrackDao = factory.getCartTrackDao();
            return cartTrackDao.findByCartIdAndTrackId(cartId, trackId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to find relation", e);
        }
    }

    public void removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            CartTrackDao cartTrackDao = factory.getCartTrackDao();
            cartTrackDao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to remove from cart", e);
        }
    }
}
