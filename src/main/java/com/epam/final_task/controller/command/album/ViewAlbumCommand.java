package com.epam.final_task.controller.command.album;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.AlbumService;
import com.epam.final_task.service.ArtistService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.service.helper.TrackStateInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ViewAlbumCommand implements Command {

    private static final String ALBUM_ID_PARAMETER = "album_id";
    private static final String ARTIST_ID_PARAMETER = "artist_id";

    private static final String USER_ATTRIBUTE = "user";
    private static final String ALBUM_ATTRIBUTE = "album";
    private static final String TRACKS_ATTRIBUTE = "tracks";
    private static final String ARTIST_ATTRIBUTE = "artist";

    private static final String ARTISTS_PAGE = "music?view_artists";

    private final TrackStateInitializer initializer;

    public ViewAlbumCommand(TrackStateInitializer initializer) {
        this.initializer = initializer;
    }

    private static final String CONTENT_PATH = "WEB-INF/view/album.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        int artistId = Integer.parseInt(request.getParameter(ARTIST_ID_PARAMETER));
        int albumId = Integer.parseInt(request.getParameter(ALBUM_ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        AlbumService albumService = factory.getAlbumService();
        ArtistService artistService = factory.getArtistService();
        Optional<Artist> artist = artistService.findById(artistId);
        Optional<Album> album = albumService.findById(albumId);
        ResponseContent responseContent;
        if (artist.isPresent()) {
            if (album.isPresent()) {
                TrackService trackService = factory.getTrackService();
                List<Track> tracks = trackService.findByAlbumId(albumId);
                if (user.getRole() == Role.CLIENT) {
                    initializer.initializeStates(tracks,(Client)user);
                }
                Collections.sort(tracks);
                request.setAttribute(ALBUM_ATTRIBUTE, album.get());
                request.setAttribute(TRACKS_ATTRIBUTE, tracks);
                request.setAttribute(ARTIST_ATTRIBUTE,artist.get());
                responseContent = new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
            }else{
                responseContent = new ResponseContent(ResponseType.REDIRECT,"music?view_albums&artist_id="+artistId);
            }
        } else  {
            responseContent = new ResponseContent(ResponseType.REDIRECT, ARTISTS_PAGE);
        }
        return responseContent;
    }
}
