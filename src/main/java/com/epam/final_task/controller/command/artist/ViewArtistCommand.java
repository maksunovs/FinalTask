package com.epam.final_task.controller.command.artist;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.OrderService;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.implementaiton.ArtistServiceImpl;
import com.epam.final_task.service.implementaiton.OrderServiceImpl;
import com.epam.final_task.service.implementaiton.TrackServiceImpl;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.util.TrackStateInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ViewArtistCommand implements Command {

    private final TrackStateInitializer initializer;

    public ViewArtistCommand(TrackStateInitializer initializer) {
        this.initializer = initializer;
    }

    private static final String CONTENT_PATH = "WEB-INF/view/artist.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        ArtistService artistService = new ArtistServiceImpl();
        Optional<Artist> artist = artistService.findById(id);
        ResponseContent responseContent;
        if (artist.isPresent()) {
            TrackService trackService = new TrackServiceImpl();
            List<Track> tracks = trackService.findByArtistId(id);
            if (user.getRole() == Role.CLIENT) {
               initializer.initializeStates(tracks,(Client)user);
            }
            request.setAttribute("artist", artist.get());
            request.setAttribute("tracks", tracks);
            responseContent = new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, ",music?command=view_artists");
        }
        return responseContent;
    }
}
