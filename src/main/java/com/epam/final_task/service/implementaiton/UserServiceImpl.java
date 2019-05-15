package com.epam.final_task.service.implementaiton;

import com.epam.final_task.model.dao.DaoFactory;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.implementation.UserDao;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.UserService;
import com.epam.final_task.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDAO();
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("login error", e);
        }
    }

    public void updateCash(BigDecimal cash, int user_id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDAO();
            userDao.updateClientCash(cash, user_id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Failed to update cash", e);
        }
    }

}
