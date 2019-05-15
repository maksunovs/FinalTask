package com.epam.final_task.model.entity;

public class Album {
    private Integer id;
    private String title;
    private Integer artistId;

    public Album(Integer id, String title, Integer artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    public Album(String title, Integer artistId) {
        this.title = title;
        this.artistId = artistId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getArtistId() {
        return artistId;
    }
}
