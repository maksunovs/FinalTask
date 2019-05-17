package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.TrackService;
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

public class ShowHomePageCommand implements Command {
    private static final String CONTENT_PATH = "WEB-INF/view/home.jsp";

    private final TrackStateInitializer initializer;

    public ShowHomePageCommand(TrackStateInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String genre = request.getParameter("genre");
        TrackService trackService = new TrackServiceImpl();
        List<Track> tracks;
        if (genre != null) {
            tracks = trackService.findByGenre(genre);
        } else {
            tracks = trackService.findAll();
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole() == Role.CLIENT) {
            initializer.initializeStates(tracks,(Client)user);
        }

        request.setAttribute("tracks", tracks);
        return new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
    }
}
