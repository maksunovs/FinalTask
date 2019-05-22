package com.epam.final_task.service;

import com.epam.final_task.model.entity.OrderTrack;
import com.epam.final_task.service.exception.ServiceException;

import java.util.Optional;

public interface OrderTrackService {

    void save(OrderTrack orderTrack) throws ServiceException;

    Optional<OrderTrack> findByOrderIdAndTrackId(int orderId, int trackId) throws ServiceException;

    void removeById(int id) throws ServiceException;
}
