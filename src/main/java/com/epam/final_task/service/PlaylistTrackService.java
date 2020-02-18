package com.epam.final_task.service;

import com.epam.final_task.service.exception.ServiceException;

import java.util.Optional;

public interface PlaylistTrackService {

    void save(int playlistId, int cartTrackId) throws ServiceException;

    Optional<PlaylistTrack> findByPlaylistIdAndTrackId(int playlistId, int trackId) throws ServiceException;

    void removeById(int id) throws ServiceException;
}
