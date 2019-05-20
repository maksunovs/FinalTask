package com.epam.final_task.controller.command.track.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Artist;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AddTrackCommand implements Command {

    private static final String ARTISTS_ATTRIBUTE = "artists";

    private static final String CONTENT_PATH = "WEB-INF/view/add_track.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        ServiceFactory factory = new ServiceFactory();
        ArtistService service = factory.getArtistService();
        List<Artist> artists = service.findAll();
        Collections.sort(artists);
        request.setAttribute(ARTISTS_ATTRIBUTE, artists);
        return new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
    }
}
