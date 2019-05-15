package com.epam.final_task.service;

import com.epam.final_task.model.entity.OrderTrack;
import com.epam.final_task.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderTrackService {

    void save(OrderTrack orderTrack, BigDecimal newValue) throws ServiceException;


    Optional<OrderTrack> findByOrderIdAndTrackId(int orderId, int trackId) throws ServiceException;

    void removeById(int id) throws ServiceException;
}
