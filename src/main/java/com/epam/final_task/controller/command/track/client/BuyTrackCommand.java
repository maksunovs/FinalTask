package com.epam.final_task.controller.command.track.client;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.enums.ResponseType;
import com.epam.final_task.model.entity.Track;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class BuyTrackCommand implements Command {

    private static final String TRACK_ID_PARAMETER = "track_id";

    private static final String USER_ATTRIBUTE = "user";

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int trackId = Integer.parseInt(request.getParameter(TRACK_ID_PARAMETER));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        ServiceFactory factory = new ServiceFactory();
        TrackService trackService = factory.getTrackService();
        Optional<Track> track = trackService.findById(trackId);
        if (track.isPresent()) {
            trackService.buyTrack(user, track.get());
        }
        return new ResponseContent(ResponseType.REDIRECT, request.getHeader(REFERER));
    }
}
