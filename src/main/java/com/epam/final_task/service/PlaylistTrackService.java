package com.epam.final_task.service;

import com.epam.final_task.model.entity.PlaylistTrack;
import com.epam.final_task.service.exception.ServiceException;

import java.util.Optional;

public interface PlaylistTrackService {

    void save(int playlistId, int audioTrackId) throws ServiceException;

    Optional<PlaylistTrack> findByPlaylistIdAndAudioTrackId(int playlistId, int audiotrackId) throws ServiceException ;

    void removeById(int id) throws ServiceException ;
}
