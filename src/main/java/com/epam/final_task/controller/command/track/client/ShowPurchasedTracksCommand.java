package com.epam.final_task.controller.command.track.client;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Client;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.model.entity.Track;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.implementaiton.TrackServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowPurchasedTracksCommand implements Command {

    private static final String CONTENT_PATH = "WEB-INF/view/purchases.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("user");
        TrackService trackService = new TrackServiceImpl();
        List<Track> tracks = trackService.findPurchasedTracks(client.getId());
        request.setAttribute("tracks", tracks);
        return new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
    }
}
