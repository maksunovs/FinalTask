package com.epam.final_task.service;

import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findByUserId(int id) throws ServiceException;

    void removeById(int id) throws ServiceException;

    void payOrder(User user, int orderId, BigDecimal value) throws ServiceException;
}
