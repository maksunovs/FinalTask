package com.epam.final_task.service;

import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserService {

    Optional<User> login(String login, String password) throws ServiceException;

    void updateCash(BigDecimal cash, int user_id) throws ServiceException;

    BigDecimal findCash(int userId) throws ServiceException;
}
