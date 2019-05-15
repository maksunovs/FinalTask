package com.epam.final_task.controller.command.playlist.admin;

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

public class SavePlaylistCommand implements Command {
    private static final String CONTENT_PATH ="music?command=view_playlists";
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String title = request.getParameter("title");
        Playlist playlist = new Playlist(title);
        PlaylistService service = new PlaylistServiceImpl();
        service.save(playlist);
        return new ResponseContent(ResponseType.REDIRECT,CONTENT_PATH);
    }
}
