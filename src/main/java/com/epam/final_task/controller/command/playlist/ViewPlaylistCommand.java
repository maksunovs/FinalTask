package com.epam.final_task.controller.command.playlist;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.PlaylistService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.service.helper.TrackStateInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ViewPlaylistCommand implements Command {

    private static final String ID_PARAMETER = "id";

    private static final String USER_ATTRIBUTE = "user";
    private static final String TRACKS_ATTRIBUTE = "tracks";
    private static final String PLAYLIST_ATTRIBUTE = "playlist";


    private static final String CONTENT_PATH = "WEB-INF/view/playlist.jsp";
    private static final String PLAYLISTS_PAGE = "music?command=view_playlists";

    private final TrackStateInitializer initializer;

    public ViewPlaylistCommand(TrackStateInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        int id = Integer.parseInt(request.getParameter(ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        PlaylistService playlistService = factory.getPlaylistService();
        Optional<Playlist> playlist = playlistService.findById(id);
        TrackService trackService = factory.getTrackService();
        List<Track> tracks = trackService.findByPlaylistId(id);
        ResponseContent responseContent;
        if (playlist.isPresent()) {
            if (user.getRole() == Role.CLIENT) {
                initializer.initializeStates(tracks, (Client) user);
            }
            request.setAttribute(TRACKS_ATTRIBUTE, tracks);
            request.setAttribute(PLAYLIST_ATTRIBUTE, playlist.get());
            responseContent = new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, PLAYLISTS_PAGE);
        }
        return responseContent;
    }
}
