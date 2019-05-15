package com.epam.final_task.service;

import com.epam.final_task.model.entity.Album;
import com.epam.final_task.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<Album> findByArtistId(int id) throws ServiceException;

    Optional<Album> findById(int id) throws ServiceException;

    void save(Album album) throws ServiceException;

    void removeById(int id) throws ServiceException;
}
