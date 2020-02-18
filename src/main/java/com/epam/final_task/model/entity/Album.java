package com.epam.final_task.model.entity;


@javax.persistence.Entity
public class Album extends Entity {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Album album = (Album) object;
        return this.id.equals(album.id) &&
                this.title.equals(album.title) &&
                this.artistId.equals(album.artistId);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (id == null ? 0 : id);
        hash = hash * prime + (title == null ? 0 : title.hashCode());
        hash = hash * prime + (artistId == null ? 0 : artistId.hashCode());
        return hash * prime;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}
