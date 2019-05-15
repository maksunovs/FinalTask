package com.epam.final_task.service;

import com.epam.final_task.model.entity.Track;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    List<Track> findAll() throws ServiceException ;

    void save(Track track) throws ServiceException ;

    List<Track> findByGenre(String genre) throws ServiceException ;

    void deleteById(int id) throws ServiceException ;

    List<Track> findByPlaylistId(int id) throws ServiceException;

    List<Track> findNotInPlaylist(int id) throws ServiceException ;
    List<Track> findByArtistId(int id) throws ServiceException ;

    List<Track> findByAlbumId(int id) throws ServiceException ;

    List<Track> findSinglesByArtistId(int id) throws ServiceException ;

    void removeTrackFromAlbum(int id) throws ServiceException ;

    void addTrackToAlbum(int trackId, int albumId) throws ServiceException ;

    List<Track> findPurchasedTracks(int userId) throws ServiceException ;

    List<Track> findOrderedTracks(int orderId) throws ServiceException ;

    Optional<Track> findById(int id) throws ServiceException ;

    void buyTrack(User user, Track track) throws ServiceException ;
}
