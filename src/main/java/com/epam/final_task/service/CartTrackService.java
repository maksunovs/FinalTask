package com.epam.final_task.service;

import com.epam.final_task.service.exception.ServiceException;

import java.util.Optional;

public interface CartTrackService {

    void save(CartTrack cartTrack) throws ServiceException;

    Optional<CartTrack> findByCartIdAndTrackId(int cartId, int trackId) throws ServiceException;

    void removeById(int id) throws ServiceException;
}
