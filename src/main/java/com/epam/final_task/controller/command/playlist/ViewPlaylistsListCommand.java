package com.epam.final_task.controller.command.playlist;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Playlist;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.PlaylistService;
import com.epam.final_task.service.implementaiton.PlaylistServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewPlaylistsListCommand implements Command {
    private static final String CONTENT_PATH = "WEB-INF/view/playlists.jsp";
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        PlaylistService service = new PlaylistServiceImpl();
        List<Playlist> playlists = service.findAll();
        request.setAttribute("playlists",playlists);
        return new ResponseContent(ResponseType.FORWARD,CONTENT_PATH);
    }
}
