package com.epam.final_task.service;

import com.epam.final_task.model.entity.Artist;
import com.epam.final_task.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ArtistService {

    void save(Artist artist) throws ServiceException ;

    Optional<Artist> findById(int id) throws ServiceException ;


    List<Artist> findAll() throws ServiceException ;

    void removeById(int id) throws ServiceException ;
}
