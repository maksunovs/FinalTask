package com.epam.final_task.model.entity;


public class Playlist extends Entity {
    private Integer id;
    private String title;

    public Playlist(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Playlist(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Playlist playlist = (Playlist) object;
        return this.id.equals(playlist.id) &&
                this.title.equals(playlist.title);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (id == null ? 0 : id);
        hash = hash * prime + (title == null ? 0 : title.hashCode());
        return hash * prime;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
