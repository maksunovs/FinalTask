package com.epam.final_task.controller.command.track.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.PlaylistTrack;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.PlaylistTrackService;
import com.epam.final_task.service.implementaiton.PlaylistTrackServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RemoveTrackFromPlaylistCommand implements Command {

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int playlistId = Integer.parseInt(request.getParameter("playlist_id"));
        int trackId = Integer.parseInt(request.getParameter("track_id"));
        PlaylistTrackService service = new PlaylistTrackServiceImpl();
        Optional<PlaylistTrack> relation = service.findByPlaylistIdAndAudioTrackId(playlistId, trackId);
        ResponseContent responseContent;
        if (relation.isPresent()) {
            int id = relation.get().getId();
            service.removeById(id);
            String contentPath = request.getHeader(REFERER);
            responseContent = new ResponseContent(ResponseType.REDIRECT, contentPath);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, "/error");
        }
        return responseContent;
    }
}
