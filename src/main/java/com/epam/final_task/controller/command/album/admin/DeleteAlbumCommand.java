package com.epam.final_task.controller.command.album.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.AlbumService;
import com.epam.final_task.service.implementaiton.AlbumServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAlbumCommand implements Command {

    private static final String CONTENT_PATH = "music?command=view_albums&artist_id=";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int albumId = Integer.parseInt(request.getParameter("album_id"));
        AlbumService service = new AlbumServiceImpl();
        service.removeById(albumId);
        String artistId = request.getParameter("artist_id");
        return new ResponseContent(ResponseType.REDIRECT,CONTENT_PATH+artistId);
    }
}
