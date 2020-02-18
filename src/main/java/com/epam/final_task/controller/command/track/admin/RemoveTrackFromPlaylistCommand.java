package com.epam.final_task.controller.command.track.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.enums.ResponseType;
import com.epam.final_task.service.PlaylistTrackService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RemoveTrackFromPlaylistCommand implements Command {

    private static final String PLAYLIST_ID_PARAMETER = "playlist_id";
    private static final String TRACK_ID_PARAMETER = "track_id";

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int playlistId = Integer.parseInt(request.getParameter(PLAYLIST_ID_PARAMETER));
        int trackId = Integer.parseInt(request.getParameter(TRACK_ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        PlaylistTrackService service = factory.PlaylistTrackservice();
        Optional<PlaylistTrack> relation = service.findByPlaylistIdAndTrackId(playlistId, trackId);
        if (relation.isPresent()) {
            int id = relation.get().getId();
            service.removeById(id);
        }
        String contentPath = request.getHeader(REFERER);
        return new ResponseContent(ResponseType.REDIRECT, contentPath);
    }
}
