package com.epam.final_task.controller.command.album.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Album;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.AlbumService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.util.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveAlbumCommand implements Command {

    private static final String CONTENT_PATH = "music?command=view_albums&artist_id=";
    private final DataValidator validator;

    public SaveAlbumCommand(DataValidator validator) {
        this.validator = validator;
    }

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int artistId = Integer.parseInt(request.getParameter("artist_id"));
        String title = request.getParameter("title");
        ServiceFactory factory = new ServiceFactory();
        AlbumService service = factory.getAlbumService();
        if (validator.validateInputText(title)) {
            service.save(new Album(title, artistId));
        }
        return new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH + artistId);
    }
}
