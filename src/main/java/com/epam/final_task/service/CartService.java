package com.epam.final_task.service;

import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartService {

    Optional<Cart> findByUserId(int id) throws ServiceException;

    void removeById(int id) throws ServiceException;

    void payCart(User user, int cartId, BigDecimal value) throws ServiceException;
}
