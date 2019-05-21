package com.epam.final_task.model.entity;


public class PlaylistTrack extends Entity {
    private Integer id;
    private Integer playlistId;
    private Integer trackId;


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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        PlaylistTrack playlistTrack = (PlaylistTrack) object;
        return this.id.equals(playlistTrack.id) &&
                this.playlistId.equals(playlistTrack.playlistId) &&
                this.trackId.equals(playlistTrack.trackId);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (id == null ? 0 : id);
        hash = hash * prime + (playlistId == null ? 0 : playlistId);
        hash = hash * prime + (trackId == null ? 0 : trackId);
        return hash * prime;
    }

    @Override
    public String toString() {
        return "PlaylistTrack{" +
                "id=" + id +
                ", playlistId=" + playlistId +
                ", trackId=" + trackId +
                '}';
    }
}
