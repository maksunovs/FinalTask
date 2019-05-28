package com.epam.final_task.controller.command.artist;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.service.helper.TrackStateInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ViewArtistCommand implements Command {

    private static final String ID_PARAMETER = "id";

    private static final String USER_ATTRIBUTE = "user";
    private static final String ARTIST_ATTRIBUTE = "artist";
    private static final String TRACKS_ATTRIBUTE = "tracks";

    private static final String ARTISTS_PAGE = "music?command=view_artists";

    private final TrackStateInitializer initializer;

    public ViewArtistCommand(TrackStateInitializer initializer) {
        this.initializer = initializer;
    }

    private static final String CONTENT_PATH = "WEB-INF/view/artist.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        int id = Integer.parseInt(request.getParameter(ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        ArtistService artistService = factory.getArtistService();
        Optional<Artist> artist = artistService.findById(id);
        ResponseContent responseContent;
        if (artist.isPresent()) {
            TrackService trackService = factory.getTrackService();
            List<Track> tracks = trackService.findByArtistId(id);
            if (user.getRole() == Role.CLIENT) {
                initializer.initializeStates(tracks, (Client) user);
            }
            Collections.sort(tracks);
            request.setAttribute(ARTIST_ATTRIBUTE, artist.get());
            request.setAttribute(TRACKS_ATTRIBUTE, tracks);
            responseContent = new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, ARTISTS_PAGE);
        }
        return responseContent;
    }
}
