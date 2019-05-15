package com.epam.final_task.service;

import com.epam.final_task.model.entity.Playlist;
import com.epam.final_task.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {

    List<Playlist> findAll() throws ServiceException;

    void save(Playlist playlist) throws ServiceException;

    void deleteById(int id) throws ServiceException;

    Optional<Playlist> findById(int id) throws ServiceException;
}
