package com.epam.final_task.controller.command.album;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Album;
import com.epam.final_task.model.entity.Artist;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.AlbumService;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.implementaiton.AlbumServiceImpl;
import com.epam.final_task.service.implementaiton.ArtistServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ViewAlbumsListCommand implements Command {

    private static final String CONTENT_PATH = "WEB-INF/view/albums.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int artistId = Integer.parseInt(request.getParameter("artist_id"));
        AlbumService albumService = new AlbumServiceImpl();
        ArtistService artistService = new ArtistServiceImpl();
        Optional<Artist> artist = artistService.findById(artistId);
        ResponseContent responseContent;
        if (artist.isPresent()) {
            List<Album> albums = albumService.findByArtistId(artistId);
            request.setAttribute("artist", artist.get());
            request.setAttribute("albums", albums);
            responseContent = new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, "music?command=view_artists");
        }
        return responseContent;
    }
}
