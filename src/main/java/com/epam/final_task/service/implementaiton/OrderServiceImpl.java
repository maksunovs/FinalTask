package com.epam.final_task.service.implementaiton;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.implementation.*;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.OrderService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    public Optional<Order> findByUserId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao dao = factory.getOrderDao();
            return dao.findByUserId(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to find order", e);
        }
    }

    public void removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao dao = factory.getOrderDao();
            dao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to remove order", e);
        }
    }

    public void payOrder(User user, int orderId, BigDecimal value) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            try {
                factory.startTransaction();
                TrackDao trackDao = factory.getTrackDao();
                List<Track> tracks = trackDao.findOrderedTracks(orderId);
                OrderTrackDao orderTrackDao = factory.getOrderTrackDao();
                UserTrackDao userTrackDao = factory.getUserTrackDao();
                List<OrderTrack> orderTrackList = orderTrackDao.findByOrderId(orderId);
                for (OrderTrack orderTrack : orderTrackList) {
                    orderTrackDao.removeById(orderTrack.getId());
                }
                for (Track track : tracks) {
                    userTrackDao.save(new UserTrack(user.getId(), track.getId()));
                }
                UserDao userDao = factory.getUserDAO();
                BigDecimal cash = ((Client) user).getCash();
                BigDecimal newCash = cash.add(value.negate());
                userDao.updateClientCash(newCash, user.getId());
                ((Client) user).setCash(newCash);
                factory.finishTransaction();
            }catch (DaoException e) {
                try{
                    factory.rollback();
                }catch (DaoException err){
                    LOGGER.error(e.getMessage());
                    throw new ServiceException("Failed to remove order", e);
                }
                LOGGER.error(e.getMessage());
                throw new ServiceException("Failed to remove order", e);
            }
        }
    }
}
