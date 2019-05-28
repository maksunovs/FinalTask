package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.model.entity.*;
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

public class ShowHomePageCommand implements Command {

    private static final String GENRE_PARAMETER = "genre";
    private static final String TRACKS_PARAMETER = "tracks";

    private static final String USER_ATTRIBUTE ="user";

    private static final String CONTENT_PATH = "WEB-INF/view/home.jsp";

    private final TrackStateInitializer initializer;

    public ShowHomePageCommand(TrackStateInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String genre = request.getParameter(GENRE_PARAMETER);
        ServiceFactory factory = new ServiceFactory();
        TrackService trackService = factory.getTrackService();
        List<Track> tracks;
        if (genre != null) {
            tracks = trackService.findByGenre(genre);
        } else {
            tracks = trackService.findAll();
        }
        Collections.sort(tracks);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (user.getRole() == Role.CLIENT) {
            initializer.initializeStates(tracks,(Client)user);
        }
        request.setAttribute(TRACKS_PARAMETER, tracks);
        return new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
    }
}
