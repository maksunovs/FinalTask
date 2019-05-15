package com.epam.final_task.controller.command.album;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.AlbumService;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.implementaiton.AlbumServiceImpl;
import com.epam.final_task.service.implementaiton.ArtistServiceImpl;
import com.epam.final_task.service.implementaiton.TrackServiceImpl;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.util.TrackStateInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ViewAlbumCommand implements Command {

    private static final String CONTENT_PATH = "WEB-INF/view/album.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int artistId = Integer.parseInt(request.getParameter("artist_id"));
        int albumId = Integer.parseInt(request.getParameter("album_id"));
        AlbumService albumService = new AlbumServiceImpl();
        ArtistService artistService = new ArtistServiceImpl();
        Optional<Artist> artist = artistService.findById(artistId);
        Optional<Album> album = albumService.findById(albumId);
        ResponseContent responseContent;
        if (artist.isPresent()) {
            if (album.isPresent()) {
                TrackService trackService = new TrackServiceImpl();
                List<Track> tracks = trackService.findByAlbumId(albumId);
                TrackStateInitializer initializer = new TrackStateInitializer();
                if (user.getRole() == Role.CLIENT) {
                    initializer.initializeStates(tracks,(Client)user);
                }
                request.setAttribute("album", album.get());
                request.setAttribute("tracks", tracks);
                request.setAttribute("artist",artist.get());
                responseContent = new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
            }else{
                responseContent = new ResponseContent(ResponseType.REDIRECT,"music?view_albums&artist_id="+artistId);
            }
        } else  {
            responseContent = new ResponseContent(ResponseType.REDIRECT, "music?view_artists");
        }
        return responseContent;
    }
}
