package com.epam.final_task.service;

import com.epam.final_task.service.impl.*;

public class ServiceFactory {
    public AlbumService getAlbumService() {
        return new AlbumServiceImpl();
    }

    public ArtistService getArtistService() {
        return new ArtistServiceImpl();
    }

    public OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    public OrderTrackService getOrderTrackService() {
        return new OrderTrackServiceImpl();
    }

    public PlaylistService getPlaylistService() {
        return new PlaylistServiceImpl();
    }

    public PlaylistTrackService PlaylistTrackservice() {
        return new PlaylistTrackServiceImpl();
    }

    public TrackService getTrackService() {
        return new TrackServiceImpl();
    }

    public UserService getUserService() {
        return new UserServiceImpl();
    }
}
