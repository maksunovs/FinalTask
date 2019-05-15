package com.epam.final_task.model.entity;

public class Playlist {
    private Integer id;
    private String title;

    public Playlist(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    public Playlist(String title){
        this.title=title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
