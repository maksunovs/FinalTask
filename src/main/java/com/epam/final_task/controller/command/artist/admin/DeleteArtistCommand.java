package com.epam.final_task.controller.command.artist.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.implementaiton.ArtistServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteArtistCommand implements Command {

    private static final String CONTENT_PATH = "music?command=view_artists";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        ServiceFactory factory = new ServiceFactory();
        ArtistService service = factory.getArtistService();
        service.removeById(id);
        return new ResponseContent(ResponseType.REDIRECT,CONTENT_PATH);
    }
}
