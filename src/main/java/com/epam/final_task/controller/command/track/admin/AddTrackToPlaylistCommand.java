package com.epam.final_task.controller.command.track.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.PlaylistTrackService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.implementaiton.PlaylistTrackServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTrackToPlaylistCommand implements Command {

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int playlistId = Integer.parseInt(request.getParameter("playlist_id"));
        int trackId = Integer.parseInt(request.getParameter("audiotrack_id"));
        ServiceFactory factory = new ServiceFactory();
        PlaylistTrackService service = factory.PlaylistTrackservice();
        service.save(playlistId, trackId);
        String contentPath = request.getHeader(REFERER);
        return new ResponseContent(ResponseType.REDIRECT, contentPath);
    }
}
