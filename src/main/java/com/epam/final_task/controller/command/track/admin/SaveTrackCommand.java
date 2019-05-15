package com.epam.final_task.controller.command.track.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Track;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.implementaiton.TrackServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class SaveTrackCommand implements Command {
    private static final String CONTENT_PATH = "music?command=add_audiotrack";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int artistId = Integer.parseInt(request.getParameter("artist_id"));
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String priceParam = request.getParameter("price");
        BigDecimal price = new BigDecimal(priceParam);
        TrackService service = new TrackServiceImpl();
        Track track = new Track(artistId, title, genre, price);
        service.save(track);
        return new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
    }
}
