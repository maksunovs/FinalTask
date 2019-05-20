package com.epam.final_task.controller.command.playlist.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Playlist;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.model.entity.Track;
import com.epam.final_task.service.PlaylistService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class EditPlaylistCommand implements Command {

    private static final String ID_PARAMETER = "id";

    private static final String PLAYLIST_ATTRIBUTE = "playlist";
    private static final String TRACKS_ATTRIBUTE = "audiotracks";

    private static final String PLAYLISTS_PAGE = "music?command=view_playlists";

    private static final String CONTENT_PATH = "WEB-INF/view/add_tracks_to_playlist.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int playlistId = Integer.parseInt(request.getParameter(ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        TrackService trackService = factory.getTrackService();
        PlaylistService playlistService = factory.getPlaylistService();
        Optional<Playlist> playlist = playlistService.findById(playlistId);
        ResponseContent responseContent;
        if (playlist.isPresent()) {
            List<Track> tracks = trackService.findNotInPlaylist(playlistId);
            request.setAttribute(PLAYLIST_ATTRIBUTE, playlist.get());
            request.setAttribute(TRACKS_ATTRIBUTE, tracks);
            responseContent = new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, PLAYLISTS_PAGE);
        }
        return responseContent;
    }
}
