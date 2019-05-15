package com.epam.final_task.model.entity;

public class PlaylistTrack {
    private Integer id;
    private Integer playlistId;
    private Integer trackId;

    public PlaylistTrack() {
    }

    public PlaylistTrack(Integer id, Integer playlistId, Integer trackId) {
        this.id = id;
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    public PlaylistTrack(Integer playlistId, Integer trackId) {
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public Integer getTrackId() {
        return trackId;
    }
}
