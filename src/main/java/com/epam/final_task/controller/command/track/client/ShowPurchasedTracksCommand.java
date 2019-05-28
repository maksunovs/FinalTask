package com.epam.final_task.controller.command.track.client;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Client;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.model.entity.Track;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ShowPurchasedTracksCommand implements Command {

    private static final String TRACKS_PARAMETER = "tracks";

    private static final String USER_ATTRIBUTE = "user";

    private static final String CONTENT_PATH = "WEB-INF/view/purchases.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute(USER_ATTRIBUTE);
        ServiceFactory factory = new ServiceFactory();
        TrackService trackService = factory.getTrackService();
        List<Track> tracks = trackService.findPurchasedTracks(client.getId());
        Collections.sort(tracks);
        request.setAttribute(TRACKS_PARAMETER, tracks);
        return new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
    }
}
