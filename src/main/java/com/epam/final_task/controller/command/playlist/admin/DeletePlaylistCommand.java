package com.epam.final_task.controller.command.playlist.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.PlaylistService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePlaylistCommand implements Command {

    private static final String ID_PARAMETER = "id";

    private static final String CONTENT_PATH = "music?command=view_playlists";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int id = Integer.parseInt(request.getParameter(ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        PlaylistService service = factory.getPlaylistService();
        service.deleteById(id);
        return new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
    }
}
