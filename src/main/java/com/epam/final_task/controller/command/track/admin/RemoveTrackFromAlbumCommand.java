package com.epam.final_task.controller.command.track.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.enums.ResponseType;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveTrackFromAlbumCommand implements Command {

    private static final String TRACK_ID_PARAMETER = "track_id";

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int trackId = Integer.parseInt(request.getParameter(TRACK_ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        TrackService service = factory.getTrackService();
        service.removeTrackFromAlbum(trackId);
        String contentPath = request.getHeader(REFERER);
        return new ResponseContent(ResponseType.REDIRECT, contentPath);
    }
}
