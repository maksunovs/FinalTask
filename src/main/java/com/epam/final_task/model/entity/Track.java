package com.epam.final_task.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Track extends Entity {
    private Integer id;
    private String artist;
    private Integer artistId;
    private String title;
    private String genre;
    private Integer albumId;
    private BigDecimal price;
    private TrackState state = TrackState.IN_STORE;

    public Track(int id, String artist, int artistId, String title, int albumId, String genre, BigDecimal price) {
        this.id = id;
        this.artist = artist;
        this.artistId = artistId;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.albumId = albumId;
    }

    public Track(int artistId, String title, String genre, BigDecimal price) {
        this.artistId = artistId;
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setState(TrackState state) {
        this.state = state;
    }

    public TrackState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id) &&
                Objects.equals(artist, track.artist) &&
                Objects.equals(artistId, track.artistId) &&
                Objects.equals(title, track.title) &&
                Objects.equals(genre, track.genre) &&
                Objects.equals(albumId, track.albumId) &&
                Objects.equals(price, track.price) &&
                state == track.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist, artistId, title, genre, albumId, price, state);
    }
}
