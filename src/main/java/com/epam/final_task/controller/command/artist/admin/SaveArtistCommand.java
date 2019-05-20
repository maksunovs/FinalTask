package com.epam.final_task.controller.command.artist.admin;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Artist;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.util.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveArtistCommand implements Command {

    private static final String CONTENT_PATH = "music?command=add_artist";
    private final DataValidator validator;

    public SaveArtistCommand(DataValidator validator) {
        this.validator = validator;
    }

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        ServiceFactory factory = new ServiceFactory();
        ArtistService service = factory.getArtistService();
        if (validator.validateInputText(name) && validator.validateCountyName(country)) {
            service.save(new Artist(name, country));
        }
        return new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
    }
}
