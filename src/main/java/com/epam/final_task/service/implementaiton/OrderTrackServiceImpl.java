package com.epam.final_task.service.implementaiton;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.implementation.OrderDao;
import com.epam.final_task.model.dao.implementation.OrderTrackDao;
import com.epam.final_task.model.entity.OrderTrack;
import com.epam.final_task.service.OrderTrackService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

public class OrderTrackServiceImpl implements OrderTrackService {


    private static final Logger LOGGER = Logger.getLogger(OrderTrackServiceImpl.class);

    public void save(OrderTrack orderTrack, BigDecimal newValue) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            OrderTrackDao orderTrackDao = factory.getOrderTrackDao();
            orderTrackDao.save(orderTrack);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to add to cart", e);
        }

    }

    public Optional<OrderTrack> findByOrderIdAndTrackId(int orderId, int trackId) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            OrderTrackDao orderTrackDao = factory.getOrderTrackDao();
            return orderTrackDao.findByOrderIdAndTrackId(orderId,trackId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to find order", e);
        }
    }

    public void removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            OrderTrackDao orderTrackDao = factory.getOrderTrackDao();
            orderTrackDao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to remove from cart", e);
        }
    }
}
