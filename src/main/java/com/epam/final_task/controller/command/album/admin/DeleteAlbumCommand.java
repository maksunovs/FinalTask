package com.epam.final_task.controller.command.album.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.AlbumService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAlbumCommand implements Command {

    private static final String ALBUM_ID_PARAMETER = "album_id";
    private static final String ARTIST_ID_PARAMETER = "artist_id";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int albumId = Integer.parseInt(request.getParameter(ALBUM_ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        AlbumService service = factory.getAlbumService();
        service.removeById(albumId);
        String artistId = request.getParameter(ARTIST_ID_PARAMETER);
        return new ResponseContent(ResponseType.REDIRECT, "music?command=view_albums&artist_id=" + artistId);
    }
}
